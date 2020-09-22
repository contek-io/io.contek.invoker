package io.contek.invoker.coinbasepro.api.websocket.user;

import io.contek.invoker.coinbasepro.api.websocket.WebSocketApi;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }
}
