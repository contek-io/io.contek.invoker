package io.contek.invoker.binanceinverse.api.websocket.market.direct;

import com.google.gson.Gson;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
final class DirectStreamMessageParser<T extends AnyWebSocketMessage>
    extends WebSocketTextMessageParser {

  private final Class<T> type;
  private final Gson gson = new Gson();

  DirectStreamMessageParser(Class<T> type) {
    this.type = type;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    return gson.fromJson(text, type);
  }
}
