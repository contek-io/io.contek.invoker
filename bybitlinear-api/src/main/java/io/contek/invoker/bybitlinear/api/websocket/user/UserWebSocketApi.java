package io.contek.invoker.bybitlinear.api.websocket.user;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final AtomicReference<OrderChannel> orderChannel = new AtomicReference<>();
  private final AtomicReference<PositionChannel> positionChannel = new AtomicReference<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public OrderChannel getOrderChannel() {
    synchronized (orderChannel) {
      if (orderChannel.get() == null) {
        orderChannel.set(new OrderChannel());
        attach(this.orderChannel.get());
      }
      return orderChannel.get();
    }
  }

  public PositionChannel getPositionChannel() {
    synchronized (positionChannel) {
      if (positionChannel.get() == null) {
        positionChannel.set(new PositionChannel());
        attach(this.positionChannel.get());
      }
      return positionChannel.get();
    }
  }
}
