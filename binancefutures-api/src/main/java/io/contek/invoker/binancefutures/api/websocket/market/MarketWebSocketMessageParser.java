package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

public final class MarketWebSocketMessageParser extends WebSocketTextMessageParser {

  private MarketWebSocketMessageParser() {
  }

  static MarketWebSocketMessageParser getInstance() {
    return MarketWebSocketMessageParser.InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {
  }

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);

      if (obj.containsKey("id")) {
        return toRequestConfirmation(obj);
      }
      if (obj.containsKey("stream")) {
        return toStreamData(obj);
      }
      return toBookTicker(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private AnyWebSocketMessage toRequestConfirmation(JsonObject obj) {
    return obj.mapTo(WebSocketCommandConfirmation.class);
  }

  private AnyWebSocketMessage toStreamData(JsonObject obj) {
    String stream = obj.getString("stream");
    if (stream.endsWith("@aggTrade")) {
      return obj.mapTo(AggTradeChannel.Message.class);
    }
    if (stream.endsWith("@depth@100ms")) {
      return obj.mapTo(DepthUpdateChannel.Message.class);
    }
    if (stream.endsWith("@forceOrder")) {
      return obj.mapTo(ForceOrderChannel.Message.class);
    }
    throw new IllegalStateException();
  }

  private AnyWebSocketMessage toBookTicker(JsonObject obj) {
    return obj.mapTo(BookTickerEvent.class);
  }

  private static class InstanceHolder {

    private static final MarketWebSocketMessageParser INSTANCE = new MarketWebSocketMessageParser();

    private InstanceHolder() {
    }
  }
}
