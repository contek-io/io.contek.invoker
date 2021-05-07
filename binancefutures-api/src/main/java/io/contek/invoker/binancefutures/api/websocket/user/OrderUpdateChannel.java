package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderUpdateChannel extends UserWebSocketChannel<OrderUpdateEvent> {

  @Override
  protected String getDisplayName() {
    return "OrderUpdateChannel";
  }

  @Override
  protected Class<OrderUpdateEvent> getMessageType() {
    return OrderUpdateEvent.class;
  }
}
