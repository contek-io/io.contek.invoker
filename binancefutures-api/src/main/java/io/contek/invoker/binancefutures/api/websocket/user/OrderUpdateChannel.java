package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.UserWebSocketChannel;

public final class OrderUpdateChannel extends UserWebSocketChannel<OrderUpdateEvent> {

  @Override
  protected String getDisplayName() {
    return "OrderUpdateChannel";
  }

  @Override
  protected Class<OrderUpdateEvent> getMessageType() {
    return OrderUpdateEvent.class;
  }

  @Override
  protected boolean accepts(OrderUpdateEvent message) {
    return true;
  }
}
