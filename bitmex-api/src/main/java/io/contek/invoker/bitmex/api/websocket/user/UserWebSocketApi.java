package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private OrderUpdateChannel orderUpdateChannel;

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderUpdateChannel getOrderUpdateChannel() {
    if (orderUpdateChannel == null) {
      this.orderUpdateChannel = new OrderUpdateChannel();
      attach(this.orderUpdateChannel);
    }
    return orderUpdateChannel;
  }
}
