package io.contek.invoker.commons.websocket;

import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;

import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
public abstract class WebSocketTextMessageParser implements IWebSocketMessageParser {

  private static final Logger log = getLogger(WebSocketTextMessageParser.class);

  @Override
  public final ParseResult parse(String text) {
    try {
      AnyWebSocketMessage message = fromText(text);
      return new ParseResult(text, message);
    } catch (Throwable t) {
      log.error("Failed to parse text message: {}.", text, t);
      throw new WebSocketIllegalMessageException(t);
    }
  }

  @Override
  public final ParseResult parse(byte[] bytes) {
    throw new UnsupportedOperationException();
  }

  protected abstract AnyWebSocketMessage fromText(String text);
}
