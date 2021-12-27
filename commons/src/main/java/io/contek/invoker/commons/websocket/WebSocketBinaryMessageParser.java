package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketBinaryMessageParser implements IWebSocketMessageParser {

  @Override
  public final ParseResult parse(String text) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final ParseResult parse(byte[] bytes) {
    String text = decode(bytes);
    return new ParseResult(text, fromText(text));
  }

  protected abstract String decode(byte[] bytes);

  protected abstract AnyWebSocketMessage fromText(String text);
}
