package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderBook25Channel extends OrderBookChannel {

  public OrderBook25Channel(OrderBook25Channel.Id id) {
    super(id);
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("orderBookL2_25.%s", symbol));
    }
  }
}
