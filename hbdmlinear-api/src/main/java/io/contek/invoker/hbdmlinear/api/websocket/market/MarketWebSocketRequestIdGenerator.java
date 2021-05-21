package io.contek.invoker.hbdmlinear.api.websocket.market;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
final class MarketWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  String generateNext() {
    return Integer.toString(count.incrementAndGet());
  }
}
