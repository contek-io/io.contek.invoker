package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderUpdateChannel extends UserWebSocketChannel<OrderUpdateEvent> {

  @Override
  protected BaseWebSocketChannelId getId() {
    return "OrderUpdateChannel";
  }

  @Override
  protected Class<OrderUpdateEvent> getMessageType() {
    return OrderUpdateEvent.class;
  }
}
