package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketMessageParser {

  ParseResult parse(String text);

  ParseResult parse(byte[] bytes);

  void register(IWebSocketComponent component);
}
