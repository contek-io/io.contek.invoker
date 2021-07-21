package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.common.WebSocketAuthenticationMessage;
import io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public final class WebSocketAuthenticator implements IWebSocketAuthenticator {

  public static final String WEBSOCKET_LOGIN = "websocket_login";
  public static final String SUBACCOUNT_KEY = "subaccount";

  private final AtomicBoolean authenticated = new AtomicBoolean();
  private final IActor actor;

  public WebSocketAuthenticator(IActor actor) {
    this.actor = actor;
  }

  @Override
  public void handshake(WebSocketSession session) {
    if (actor.getCredential().isAnonymous()) {
      return;
    }

    WebSocketAuthenticationMessage request = new WebSocketAuthenticationMessage();
    long currentTimeStamp = actor.getClock().instant().getEpochSecond() * 1000;
    request.op = WebSocketOutboundKeys._login;
    request.args = new WebSocketAuthenticationMessage.Args();
    request.args.key = actor.getCredential().getApiKeyId();
    request.args.sign = actor.getCredential().sign(currentTimeStamp + WEBSOCKET_LOGIN );
    request.args.time = currentTimeStamp;
    if (actor.getCredential().getSubAccount() != null) {
      request.args.subaccount = actor.getCredential().getSubAccount();
    }

    session.send(request);
    // There will be no confirmation message after authentication.
    authenticated.set(true);
  }

  @Override
  public boolean isCompleted() {
    return actor.getCredential().isAnonymous() || authenticated.get();
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {

  }

  @Override
  public void afterDisconnect() {
    authenticated.set(false);
  }
}
