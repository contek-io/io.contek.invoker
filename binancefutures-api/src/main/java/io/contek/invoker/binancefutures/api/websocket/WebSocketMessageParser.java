package io.contek.invoker.binancefutures.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.binancefutures.api.websocket.market.AggTradeChannel;
import io.contek.invoker.binancefutures.api.websocket.market.BookTickerEvent;
import io.contek.invoker.binancefutures.api.websocket.market.DepthUpdateChannel;
import io.contek.invoker.binancefutures.api.websocket.market.ForceOrderChannel;
import io.contek.invoker.binancefutures.api.websocket.user.AccountUpdateEvent;
import io.contek.invoker.binancefutures.api.websocket.user.LeverageUpdateEvent;
import io.contek.invoker.binancefutures.api.websocket.user.MarginCallEvent;
import io.contek.invoker.binancefutures.api.websocket.user.OrderUpdateEvent;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
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
    if (obj.has("e")) {
      return toUserData(obj);
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
    if (stream.endsWith("@depth@100ms")) {
      return gson.fromJson(obj, DepthUpdateChannel.Message.class);
    }
    if (stream.endsWith("@forceOrder")) {
      return gson.fromJson(obj, ForceOrderChannel.Message.class);
    }
    throw new IllegalStateException();
  }

  private AnyWebSocketMessage toUserData(JsonObject obj) {
    String eventType = obj.get("e").getAsString();
    switch (eventType) {
      case "ACCOUNT_UPDATE":
        return gson.fromJson(obj, AccountUpdateEvent.class);
      case "ORDER_TRADE_UPDATE":
        return gson.fromJson(obj, OrderUpdateEvent.class);
      case "ACCOUNT_CONFIG_UPDATE":
        return gson.fromJson(obj, LeverageUpdateEvent.class);
      case "MARGIN_CALL":
        return gson.fromJson(obj, MarginCallEvent.class);
      default:
        throw new IllegalStateException("Unrecognized event type: " + eventType);
    }
  }

  private AnyWebSocketMessage toBookTicker(JsonObject obj) {
    return gson.fromJson(obj, BookTickerEvent.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
