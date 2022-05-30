package io.contek.invoker.commons.websocket;

import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class WebSocketTextMessageParser implements IWebSocketMessageParser {

  private static final Logger log = getLogger(WebSocketTextMessageParser.class);

  @Override
  public final ParseResult parseText(String text) {
    try {
      AnyWebSocketMessage message = fromText(text);
      return new ParseResult(text, message);
    } catch (Throwable t) {
      log.error("Failed to parse text message: {}.", text, t);
      throw new WebSocketIllegalMessageException(t);
    }
  }

  @Override
  public final ParseResult parseBinary(Buffer binary) {
    throw new UnsupportedOperationException();
  }

  protected abstract AnyWebSocketMessage fromText(String text);
}
