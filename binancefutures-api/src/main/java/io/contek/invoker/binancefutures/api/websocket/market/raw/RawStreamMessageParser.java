package io.contek.invoker.binancefutures.api.websocket.market.raw;

import com.google.gson.Gson;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class RawStreamMessageParser<T extends AnyWebSocketMessage>
    extends WebSocketTextMessageParser {

  private final Class<T> type;
  private final Gson gson = new Gson();

  RawStreamMessageParser(Class<T> type) {
    this.type = type;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    return gson.fromJson(text, type);
  }
}
