package io.contek.invoker.commons.websocket;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.impl.VertxByteBufAllocator;

public class AutoReconnectWebsocket {

  public AutoReconnectWebsocket(WebSocketContext webSocketContext) {
    final ByteBuf buf = VertxByteBufAllocator.DEFAULT.directBuffer();
  }
}
