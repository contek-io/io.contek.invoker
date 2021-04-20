package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final OrderUpdateChannel orderUpdateChannel;

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
    this.orderUpdateChannel = new OrderUpdateChannel();
    attach(this.orderUpdateChannel);
  }

  public OrderUpdateChannel getOrderUpdateChannel() {
    return orderUpdateChannel;
  }
}
