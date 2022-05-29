package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import java.util.concurrent.atomic.AtomicInteger;

public final class NotificationWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  public String generateNext() {
    return Integer.toString(count.incrementAndGet());
  }
}
