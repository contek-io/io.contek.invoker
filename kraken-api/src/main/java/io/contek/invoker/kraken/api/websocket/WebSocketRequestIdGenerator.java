package io.contek.invoker.kraken.api.websocket;

import java.util.concurrent.atomic.AtomicInteger;

public final class WebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public int generateNext() {
    return count.incrementAndGet();
  }
}
