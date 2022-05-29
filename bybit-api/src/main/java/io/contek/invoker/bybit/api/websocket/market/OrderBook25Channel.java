package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;

public final class OrderBook25Channel extends OrderBookChannel<OrderBook25Channel.Id> {

  public OrderBook25Channel(OrderBook25Channel.Id id) {
    super(id);
  }

  public static final class Id extends WebSocketChannelId<OrderBookChannel.Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("orderBookL2_25.%s", symbol));
    }
  }
}
