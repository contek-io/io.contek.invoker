package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class ParseResult {

  private final String stringValue;
  private final AnyWebSocketMessage message;

  public ParseResult(String stringValue, AnyWebSocketMessage message) {
    this.stringValue = stringValue;
    this.message = message;
  }

  public String getStringValue() {
    return stringValue;
  }

  public AnyWebSocketMessage getMessage() {
    return message;
  }
}
