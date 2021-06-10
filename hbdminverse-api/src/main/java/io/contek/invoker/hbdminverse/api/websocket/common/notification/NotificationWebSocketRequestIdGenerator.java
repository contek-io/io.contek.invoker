package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public final class NotificationWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public String generateNext() {
    return Integer.toString(count.incrementAndGet());
  }
}
