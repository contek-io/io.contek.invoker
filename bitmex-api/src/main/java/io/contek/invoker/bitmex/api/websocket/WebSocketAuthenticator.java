package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketAuthKeyExpiresResponse;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  private static final Duration EXPIRE_DELAY = Duration.ofSeconds(5);

  private final ICredential credential;
  private final Clock clock;

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
    long expires = clock.instant().plus(EXPIRE_DELAY).getEpochSecond();
    String payload = "GET/realtime" + expires;
    String signature = credential.sign(payload);

    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = WebSocketRequestOperationKeys._authKeyExpires;
    request.args = ImmutableList.of(key, expires, signature);

    session.send(request);
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
    if (!(message instanceof WebSocketAuthKeyExpiresResponse)) {
      return;
    }

    WebSocketAuthKeyExpiresResponse confirmation = (WebSocketAuthKeyExpiresResponse) message;
    if (!confirmation.success) {
      throw new IllegalStateException();
    }
    authenticated.set(true);
  }

  @Override
  public void afterDisconnect() {
    authenticated.set(false);
  }
}
