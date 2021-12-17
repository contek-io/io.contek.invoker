package io.contek.invoker.bybitlinear.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.security.ICredential;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.contek.invoker.bybitlinear.api.websocket.common.constants.WebSocketOperationKeys._auth;
import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  private static final Duration EXPIRE_DELAY = Duration.ofSeconds(30);
  private static final Logger log = getLogger(WebSocketAuthenticator.class);

  private final ICredential credential;
  private final Clock clock;

  private final AtomicBoolean pending = new AtomicBoolean();
  private final AtomicBoolean authenticated = new AtomicBoolean();

  WebSocketAuthenticator(ICredential credential, Clock clock) {
    this.credential = credential;
    this.clock = clock;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (credential.isAnonymous()) {
      return;
    }
    String key = credential.getApiKeyId();

    // Add more time to account for network delay.
    String expires = Long.toString(clock.instant().plus(EXPIRE_DELAY).toEpochMilli());
    String payload = "GET/realtime" + expires;
    String signature = credential.sign(payload);

    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _auth;
    request.args = ImmutableList.of(key, expires, signature);

    log.info("Requesting authentication for {}.", credential.getApiKeyId());
    session.send(request);
    pending.set(true);
  }

  @Override
  public boolean isPending() {
    return pending.get();
  }

  @Override
  public boolean isCompleted() {
    return credential.isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (isCompleted()) {
      return;
    }

    if (!(message instanceof WebSocketOperationResponse)) {
      return;
    }

    WebSocketOperationResponse confirmation = (WebSocketOperationResponse) message;
    if (!confirmation.request.op.equals(_auth)) {
      return;
    }

    pending.set(false);
    if (!confirmation.success) {
      throw new IllegalStateException();
    }

    authenticated.set(true);
    log.info("Authentication for {} completed.", credential.getApiKeyId());
  }

  @Override
  public void afterDisconnect() {
    pending.set(false);
    authenticated.set(false);
  }
}
