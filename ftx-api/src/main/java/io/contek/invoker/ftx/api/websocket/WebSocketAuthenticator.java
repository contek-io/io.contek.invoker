package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.common.WebSocketAuthenticationMessage;
import io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  public static final String WEBSOCKET_LOGIN = "websocket_login";
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
    long currentTimeStamp = clock.instant().getEpochSecond() * 1000;
    request.op = WebSocketOutboundKeys._login;
    request.args = new WebSocketAuthenticationMessage.Args();
    request.args.key = credential.getApiKeyId();
    request.args.sign = credential.sign(currentTimeStamp + WEBSOCKET_LOGIN);
    request.args.time = currentTimeStamp;
    request.args.subaccount = credential.getProperties().get(ApiKey.SUBACCOUNT_PROPERTY_KEY);

    session.send(request);
    // There will be no confirmation message after authentication.
    authenticated.set(true);
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
