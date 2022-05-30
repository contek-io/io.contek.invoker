package io.contek.invoker.commons.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.RequestContext;
import io.contek.invoker.commons.actor.http.HttpBusyException;
import io.contek.invoker.commons.actor.http.HttpInterruptedException;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.SocketAddress;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseWebSocketApi implements IWebSocketApi {

  private static final Logger log = getLogger(BaseWebSocketApi.class);

  private final IActor actor;
  private final IWebSocketMessageParser parser;
  private final IWebSocketAuthenticator authenticator;
  private final IWebSocketLiveKeeper liveKeeper;

  private final Handler handler = new Handler();
  private final WebSocketComponentManager components = new WebSocketComponentManager();
  private WebSocketSession sessionHolder = null;
  private long timerId = -1;

  protected BaseWebSocketApi(
      IActor actor,
      IWebSocketMessageParser parser,
      IWebSocketAuthenticator authenticator,
      IWebSocketLiveKeeper liveKeeper) {
    this.actor = actor;
    this.parser = parser;
    this.authenticator = authenticator;
    this.liveKeeper = liveKeeper;
  }

  @Override
  public final void attach(IWebSocketComponent component) {
    actor.runOnContext(
        () -> {
          parser.register(component);
          components.attach(component);
          activate();
        });
  }

  protected final IWebSocketMessageParser getParser() {
    return parser;
  }

  protected final IWebSocketAuthenticator getAuthenticator() {
    return authenticator;
  }

  protected final IWebSocketLiveKeeper getLiveKeeper() {
    return liveKeeper;
  }

  protected abstract ImmutableList<TypedPermitRequest> getRequiredQuotas();

  protected abstract WebSocketCall createCall(ICredential credential);

  protected abstract void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException;

  void forwardTextMessage(String text) {
    ParseResult result = parser.parseText(text);
    forwardParsedMessage(result);
  }

  void forwardBinaryMessage(Buffer binary) {
    ParseResult result = parser.parseBinary(binary);
    forwardParsedMessage(result);
  }

  void forwardParsedMessage(ParseResult result) {
    try {
      AnyWebSocketMessage message = result.getMessage();
      checkErrorMessage(message);
      WebSocketSession session = sessionHolder;
      liveKeeper.onMessage(message, session);
      if (!authenticator.isCompleted()) {
        authenticator.onMessage(message, session);
      }
      components.onMessage(message, session);
    } catch (IllegalStateException e) {
      log.error("Failed to handle message: {}.", result.getStringValue(), e);
      throw new WebSocketIllegalMessageException(e);
    }
  }

  void connect() {
    if (sessionHolder == null) {
      WebSocketCall call = createCall(actor.credential());
      try (RequestContext context =
          actor.requestContext(getClass().getSimpleName(), getRequiredQuotas())) {
        activate();

        call.submit(context.client())
            .onSuccess(
                webSocket -> {
                  sessionHolder = new WebSocketSession(webSocket);

                  handler.onOpen(webSocket.remoteAddress());
                  webSocket.textMessageHandler(handler::onTextMessage);
                  webSocket.binaryMessageHandler(handler::onBinaryMessage);
                  webSocket.exceptionHandler(handler::onException);
                  webSocket.closeHandler(
                      __ -> handler.onClose(webSocket.closeStatusCode(), webSocket.closeReason()));
                })
            .onFailure(handler::onFailureConnection);

      } catch (AcquireTimeoutException e) {
        throw new HttpBusyException(e);
      } catch (InterruptedException e) {
        throw new HttpInterruptedException(e);
      }
    }
  }

  void afterDisconnect() {
    sessionHolder = null;
    components.afterDisconnect();
    liveKeeper.afterDisconnect();
    authenticator.afterDisconnect();
  }

  void heartbeat() {
    try {
      WebSocketSession session = sessionHolder;
      components.refresh();
      if (session == null) {
        if (!components.hasComponent()) {
          deactivate();
          return;
        }
        if (components.hasActiveComponent()) {
          connect();
        }
        return;
      }

      if (!components.hasActiveComponent()) {
        log.info("No active components. Closing session.");
        session.close();
        return;
      }

      try {
        liveKeeper.onHeartbeat(session);
      } catch (WebSocketSessionInactiveException e) {
        log.warn("WebSocket session is inactive", e);
        session.close();
      }

      if (authenticator.isPending()) {
        return;
      }
      if (!authenticator.isCompleted()) {
        authenticator.handshake(session);
        return;
      }

      components.heartbeat(session);
    } catch (Exception e) {
      log.error("Heartbeat failed.", e);
    }
  }

  void activate() {
    if (timerId == -1) {
      timerId = actor.vertx().setPeriodic(1000, __ -> heartbeat());
    }
  }

  void deactivate() {
    if (timerId != -1) {
      final boolean success = actor.vertx().cancelTimer(timerId);
      timerId = -1;
      if (!success) {
        throw new RuntimeException("Can't stop periodic timer with id: " + timerId);
      }
    }
  }

  final class Handler {

    void onClose(Short code, String reason) {
      log.info("Session is closed: {} {}.", code, reason);
      try {
        afterDisconnect();
      } catch (Throwable t) {
        log.error("Failed to handle closed session.", t);
      }
    }

    void onException(Throwable t) {
      log.warn(t.getMessage(), t);

      try {
        afterDisconnect();
      } catch (Throwable t2) {
        log.error("Failed to handle failure.", t2);
      }
    }

    void onTextMessage(String text) {
      forwardTextMessage(text);
    }

    void onBinaryMessage(Buffer binary) {
      forwardBinaryMessage(binary);
    }

    void onFailureConnection(Throwable throwable) {
      log.warn("Failure when connecting", throwable);
      afterDisconnect();
    }

    void onOpen(SocketAddress remoteAddress) {
      log.info("Session is open: {}.", remoteAddress);
    }
  }
}
