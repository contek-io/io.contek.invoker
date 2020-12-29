package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketAuthKeyExpiresConfirmation;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketRequest;
import io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.api.websocket.WebSocketSession;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public final class WebSocketAuthenticator implements IWebSocketAuthenticator {

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
    String key = credential.getApiKeyId();

    long expires = clock.instant().getEpochSecond();
    String payload = "GET/realtime" + expires;
    String signature = credential.sign(payload);

    WebSocketRequest request = new WebSocketRequest();
    request.op = WebSocketRequestOperationKeys._authKeyExpires;
    request.args = ImmutableList.of(key, expires, signature);

    session.send(request);
  }

  @Override
  public boolean isCompleted() {
    return credential.isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message) {
    if (isCompleted()) {
      return;
    }
    if (!(message instanceof WebSocketAuthKeyExpiresConfirmation)) {
      return;
    }

    WebSocketAuthKeyExpiresConfirmation confirmation =
        (WebSocketAuthKeyExpiresConfirmation) message;
    authenticated.set(true);
  }

  @Override
  public void afterDisconnect() {
    authenticated.set(false);
  }
}
