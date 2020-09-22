package io.contek.invoker.bitmex.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bitmex.api.websocket.common.*;
import io.contek.invoker.bitmex.api.websocket.market.*;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketTables.*;

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
    if (obj.has("table")) {
      return toTableMessage(obj);
    }
    if (obj.has("request")) {
      return toRequestConfirmation(obj);
    }
    if (obj.has("info")) {
      return toInfo(obj);
    }
    throw new IllegalArgumentException(text);
  }

  private WebSocketTableDataMessage<?> toTableMessage(JsonObject obj) {
    String tableName = obj.get("table").getAsString();
    switch (tableName) {
      case orderBookL2:
        return gson.fromJson(obj, OrderBookL2Channel.Message.class);
      case quote:
        return gson.fromJson(obj, QuoteChannel.Message.class);
      case trade:
        return gson.fromJson(obj, TradeChannel.Message.class);
      case tradeBin1m:
      case tradeBin5m:
      case tradeBin1h:
      case tradeBin1d:
        return gson.fromJson(obj, TradeBinChannel.Message.class);
      case liquidation:
        return gson.fromJson(obj, LiquidationChannel.Message.class);
      default:
    }
    throw new IllegalArgumentException(obj.toString());
  }

  private WebSocketRequestConfirmation toRequestConfirmation(JsonObject obj) {
    if (obj.has("subscribe")) {
      return gson.fromJson(obj, WebSocketSubscribeConfirmation.class);
    }
    if (obj.has("unsubscribe")) {
      return gson.fromJson(obj, WebSocketUnsubscribeConfirmation.class);
    }
    if (obj.has("authKeyExpires")) {
      return gson.fromJson(obj, WebSocketAuthKeyExpiresConfirmation.class);
    }
    throw new IllegalArgumentException(obj.toString());
  }

  private WebSocketInfo toInfo(JsonObject obj) {
    return gson.fromJson(obj, WebSocketInfo.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
