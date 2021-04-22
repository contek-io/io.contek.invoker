package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final AtomicReference<OrderUpdateChannel> orderUpdateChannel = new AtomicReference<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderUpdateChannel getOrderUpdateChannel() {
    synchronized (orderUpdateChannel) {
      if (orderUpdateChannel.get() == null) {
        orderUpdateChannel.set(new OrderUpdateChannel());
        attach(this.orderUpdateChannel.get());
      }
      return orderUpdateChannel.get();
    }
  }
}
