package io.contek.invoker.bybit.api.websocket.market;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderBook200Channel extends OrderBookChannel {

  public static final String TOPIC_PREFIX = "orderBook_200.100ms";

  public OrderBook200Channel(String symbol) {
    super(TOPIC_PREFIX + '.' + symbol);
  }
}
