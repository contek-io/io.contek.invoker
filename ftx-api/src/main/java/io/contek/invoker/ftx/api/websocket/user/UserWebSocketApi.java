package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.ftx.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final AtomicReference<FillsChannel> fillsChannel = new AtomicReference<>();
  private final AtomicReference<OrdersChannel> ordersChannel = new AtomicReference<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public FillsChannel getFillsChannel() {
    synchronized (fillsChannel) {
      if (fillsChannel.get() == null) {
        this.fillsChannel.set(new FillsChannel());
        attach(this.fillsChannel.get());
      }
      return fillsChannel.get();
    }
  }

  public OrdersChannel getOrdersChannel() {
    synchronized (ordersChannel) {
      if (ordersChannel.get() == null) {
        this.ordersChannel.set(new OrdersChannel());
        attach(this.ordersChannel.get());
      }
      return ordersChannel.get();
    }
  }
}
