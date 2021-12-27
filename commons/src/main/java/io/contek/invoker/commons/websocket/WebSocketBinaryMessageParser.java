package io.contek.invoker.commons.websocket;

import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;

import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
public abstract class WebSocketBinaryMessageParser implements IWebSocketMessageParser {

  private static final Logger log = getLogger(WebSocketBinaryMessageParser.class);

  @Override
  public final ParseResult parse(String text) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final ParseResult parse(byte[] bytes) {
    String text;
    try {
      text = decode(bytes);
    } catch (Throwable t) {
      log.error("Failed to decode binary message: size {}.", bytes.length, t);
      throw new WebSocketIllegalMessageException(t);
    }

    try {
      AnyWebSocketMessage message = fromText(text);
      return new ParseResult(text, message);
    } catch (Throwable t) {
      log.error("Failed to parse binary message: {}.", text, t);
      throw new WebSocketIllegalMessageException(t);
    }
  }

  protected abstract String decode(byte[] bytes);

  protected abstract AnyWebSocketMessage fromText(String text);
}
