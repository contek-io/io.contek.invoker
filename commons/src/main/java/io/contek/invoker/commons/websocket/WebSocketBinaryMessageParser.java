package io.contek.invoker.commons.websocket;

import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class WebSocketBinaryMessageParser implements IWebSocketMessageParser {

  private static final Logger log = getLogger(WebSocketBinaryMessageParser.class);

  @Override
  public final ParseResult parseText(String text) {
    throw new UnsupportedOperationException();
  }

  @Override
  public final ParseResult parseBinary(Buffer binary) {
    String text;
    try {
      text = decode(binary);
    } catch (Throwable t) {
      log.error("Failed to decode binary message: size {}.", binary.length(), t);
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

  protected abstract String decode(Buffer binary);

  protected abstract AnyWebSocketMessage fromText(String text);
}
