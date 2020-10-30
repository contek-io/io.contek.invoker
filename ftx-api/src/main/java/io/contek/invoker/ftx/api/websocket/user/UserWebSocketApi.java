package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }
}
