package io.contek.invoker.binancespot.api.websocket.market;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.Immutable;
import java.util.List;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.*;

@Immutable
public final class MarketWebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = GsonFactory.buildGson();

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
    return switch (type) {
      case _bookTicker -> gson.fromJson(obj, BookTickerChannel.Message.class);
      case _trade -> gson.fromJson(obj, TradeChannel.Message.class);
      case _aggTrade -> gson.fromJson(obj, AggTradeChannel.Message.class);
      case _depth -> gson.fromJson(obj, DepthDiffChannel.Message.class);
      case _depth5, _depth10, _depth20 -> gson.fromJson(obj, DepthPartialChannel.Message.class);
      default -> throw new IllegalStateException();
    };
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
