package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public final class MarketDataWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public String generateNext() {
    return Integer.toString(count.incrementAndGet());
  }
}
