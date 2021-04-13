package io.contek.invoker.binancefutures.api.websocket.user;

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
