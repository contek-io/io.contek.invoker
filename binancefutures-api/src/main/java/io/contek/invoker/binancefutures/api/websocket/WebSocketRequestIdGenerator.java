package io.contek.invoker.binancefutures.api.websocket;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class WebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public int getNextRequestId() {
    return count.incrementAndGet();
  }
}
