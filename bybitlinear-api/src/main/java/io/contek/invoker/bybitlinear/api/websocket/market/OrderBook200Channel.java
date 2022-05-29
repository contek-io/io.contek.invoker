package io.contek.invoker.bybitlinear.api.websocket.market;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;

public final class OrderBook200Channel extends OrderBookChannel<OrderBook200Channel.Id> {

  public OrderBook200Channel(OrderBook200Channel.Id id) {
    super(id);
  }

  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("orderBook_200.100ms.%s", symbol));
    }
  }
}
