package io.contek.invoker.commons.websocket;

import io.vertx.core.buffer.Buffer;

public interface IWebSocketMessageParser {

  ParseResult parseText(String text);

  ParseResult parseBinary(Buffer binary);

  void register(IWebSocketComponent component);
}
