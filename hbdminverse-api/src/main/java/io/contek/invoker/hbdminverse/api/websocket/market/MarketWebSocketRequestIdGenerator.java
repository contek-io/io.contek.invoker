package io.contek.invoker.hbdminverse.api.websocket.market;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
final class MarketWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  int generateNext() {
    return count.incrementAndGet();
  }
}
