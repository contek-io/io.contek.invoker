package io.contek.invoker.binancefutures.api.websocket.market;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.Immutable;
import java.util.List;

@Immutable
public final class MarketWebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

  static MarketWebSocketMessageParser getInstance() {
    return MarketWebSocketMessageParser.InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
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
    List<String> parts = Splitter.on('@').splitToList(stream);
    if (parts.size() < 2) {
      throw new IllegalArgumentException(stream);
    }
    String type = parts.get(1);
    switch (type) {
      case "trade":
        return gson.fromJson(obj, TradeChannel.Message.class);
      case "aggTrade":
        return gson.fromJson(obj, AggTradeChannel.Message.class);
      case "depth":
        return gson.fromJson(obj, DepthUpdateChannel.Message.class);
      case "forceOrder":
        return gson.fromJson(obj, ForceOrderChannel.Message.class);
    }
    throw new IllegalStateException();
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
