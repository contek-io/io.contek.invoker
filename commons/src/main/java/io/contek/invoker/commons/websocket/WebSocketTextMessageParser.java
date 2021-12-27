package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketTextMessageParser implements IWebSocketMessageParser {

  @Override
  public final ParseResult parse(String text) {
    return new ParseResult(text, fromText(text));
  }

  @Override
  public final ParseResult parse(byte[] bytes) {
    throw new UnsupportedOperationException();
  }

  protected abstract AnyWebSocketMessage fromText(String text);
}
