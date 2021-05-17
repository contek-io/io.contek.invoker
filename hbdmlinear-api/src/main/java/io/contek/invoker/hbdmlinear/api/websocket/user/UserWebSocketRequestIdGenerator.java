package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
final class UserWebSocketRequestIdGenerator {

  private final AtomicInteger count = new AtomicInteger(0);

  int generateNext() {
    return count.incrementAndGet();
  }
}
