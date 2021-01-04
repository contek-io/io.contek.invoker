package io.contek.invoker.binancedelivery.api.websocket.user;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor);
    this.context = context;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    throw new UnsupportedOperationException();
  }
}
