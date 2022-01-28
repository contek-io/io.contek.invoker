package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.common.WebSocketAuthenticationMessage;
import io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys;
import io.contek.invoker.security.ICredential;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.contek.invoker.ftx.api.rest.RestRequest.FTX_SUBACCOUNT_KEY;
import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
public final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  public static final String WEBSOCKET_LOGIN = "websocket_login";

  private static final Logger log = getLogger(WebSocketAuthenticator.class);

  private final ICredential credential;
  private final Clock clock;

  private final AtomicBoolean authenticated = new AtomicBoolean();

  public WebSocketAuthenticator(ICredential credential, Clock clock) {
    this.credential = credential;
    this.clock = clock;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (credential.isAnonymous()) {
      return;
    }

    WebSocketAuthenticationMessage request = new WebSocketAuthenticationMessage();
    long currentMillis = clock.instant().toEpochMilli();
    request.op = WebSocketOutboundKeys._login;
    request.args = new WebSocketAuthenticationMessage.Args();
    request.args.key = credential.getApiKeyId();
    request.args.sign = credential.sign(currentMillis + WEBSOCKET_LOGIN);
    request.args.time = currentMillis;
    request.args.subaccount = credential.getProperties().get(FTX_SUBACCOUNT_KEY);

    log.info("Requesting authentication for {}.", credential.getApiKeyId());
    session.send(request);
    authenticated.set(true);
  }

  @Override
  public boolean isPending() {
    return false;
  }

  @Override
  public boolean isCompleted() {
    return credential.isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

  @Override
  public void afterDisconnect() {
    authenticated.set(false);
  }
}
