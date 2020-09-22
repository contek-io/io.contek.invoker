package io.contek.invoker.bybit.api.websocket.market;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderBook25Channel extends OrderBookChannel {

  public static final String TOPIC_PREFIX = "orderBook_25";

  public OrderBook25Channel(String symbol) {
    super(TOPIC_PREFIX + '.' + symbol);
  }
}
