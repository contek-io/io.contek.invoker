package io.contek.invoker.binancedelivery.api.websocket.market;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class MarketWebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static MarketWebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("id")) {
      return toRequestConfirmation(obj);
    }
    if (obj.has("stream")) {
      return toStreamData(obj);
    }
    return toBookTicker(obj);
  }

  private AnyWebSocketMessage toRequestConfirmation(JsonObject obj) {
    return gson.fromJson(obj, WebSocketCommandConfirmation.class);
  }

  private AnyWebSocketMessage toStreamData(JsonObject obj) {
    String stream = obj.get("stream").getAsString();
    if (stream.endsWith("@aggTrade")) {
      return gson.fromJson(obj, AggTradeChannel.Message.class);
    }
    if (stream.endsWith("@depth") || stream.endsWith("@depth@100ms") || stream.endsWith("@depth@500ms")) {
      return gson.fromJson(obj, DepthUpdateChannel.Message.class);
    }
    if (stream.endsWith("@forceOrder")) {
      return gson.fromJson(obj, ForceOrderChannel.Message.class);
    }
    if (stream.contains("@kline_")) {
      return gson.fromJson(obj, KlineChannel.Message.class);
    }
    if (stream.endsWith("@ticker")) {
      return gson.fromJson(obj, TickerChannel.Message.class);
    }
    throw new IllegalStateException("Unknown stream " + stream);
  }

  private AnyWebSocketMessage toBookTicker(JsonObject obj) {
    return gson.fromJson(obj, BookTickerEvent.class);
  }

  private MarketWebSocketMessageParser() {}

  @Immutable
  private static class InstanceHolder {

    private static final MarketWebSocketMessageParser INSTANCE = new MarketWebSocketMessageParser();

    private InstanceHolder() {}
  }
}
