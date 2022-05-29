package io.contek.invoker.commons.websocket;

public interface IWebSocketMessageParser {

  ParseResult parse(String text);

  ParseResult parse(byte[] bytes);

  void register(IWebSocketComponent component);
}
