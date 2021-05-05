package io.contek.invoker.kraken.api.websocket;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public final class WebSocketRequestIdGenerator {

  private static final AtomicInteger count = new AtomicInteger(0);

  public int generateNext() {
    return count.incrementAndGet();
  }
}
