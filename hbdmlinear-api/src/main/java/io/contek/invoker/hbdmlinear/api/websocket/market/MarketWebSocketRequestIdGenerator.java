package io.contek.invoker.hbdmlinear.api.websocket.market;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
final class MarketWebSocketRequestIdGenerator {

  private static final AtomicInteger count = new AtomicInteger(0);

  int generateNext() {
    return count.incrementAndGet();
  }
}
