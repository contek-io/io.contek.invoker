package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context, context.getPingInterval());
  }
}
