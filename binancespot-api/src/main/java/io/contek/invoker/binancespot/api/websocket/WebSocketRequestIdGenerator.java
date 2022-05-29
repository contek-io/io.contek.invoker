package io.contek.invoker.binancespot.api.websocket;

import java.util.concurrent.atomic.AtomicInteger;

public class WebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public int getNextRequestId() {
    return count.incrementAndGet();
  }
}
