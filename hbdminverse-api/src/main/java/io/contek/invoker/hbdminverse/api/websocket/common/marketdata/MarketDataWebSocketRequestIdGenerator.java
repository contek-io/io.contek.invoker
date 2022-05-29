package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import java.util.concurrent.atomic.AtomicInteger;

public final class MarketDataWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public String generateNext() {
    return Integer.toString(count.incrementAndGet());
  }
}
