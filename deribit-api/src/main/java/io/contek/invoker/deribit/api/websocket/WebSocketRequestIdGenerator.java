package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public final class WebSocketRequestIdGenerator {

  private final WebSocketMessageParser parser;

  private final AtomicInteger count = new AtomicInteger(0);

  public WebSocketRequestIdGenerator(WebSocketMessageParser parser) {
    this.parser = parser;
  }

  public int getNextRequestId(Class<? extends WebSocketResponse<?>> type) {
    int id = count.incrementAndGet();
    parser.register(id, type);
    return id;
  }
}
