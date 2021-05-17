package io.contek.invoker.bitmex.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bitmex.api.websocket.common.*;
import io.contek.invoker.bitmex.api.websocket.market.*;
import io.contek.invoker.bitmex.api.websocket.user.OrderChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys.*;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketTableKeys.*;

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
      return toOperationResponse(obj);
    }
    if (obj.has("info")) {
      return toInfo(obj);
    }
    throw new IllegalArgumentException(text);
  }

  private WebSocketTableDataMessage<?> toTableMessage(JsonObject obj) {
    String tableName = obj.get("table").getAsString();
    switch (tableName) {
      case _orderBookL2:
        return gson.fromJson(obj, OrderBookL2Channel.Message.class);
      case _quote:
        return gson.fromJson(obj, QuoteChannel.Message.class);
      case _trade:
        return gson.fromJson(obj, TradeChannel.Message.class);
      case _instrument:
        return gson.fromJson(obj, InstrumentChannel.Message.class);
      case _tradeBin1m:
      case _tradeBin5m:
      case _tradeBin1h:
      case _tradeBin1d:
        return gson.fromJson(obj, TradeBinChannel.Message.class);
      case _liquidation:
        return gson.fromJson(obj, LiquidationChannel.Message.class);
      case _order:
        return gson.fromJson(obj, OrderChannel.Message.class);
      default:
    }
    throw new IllegalArgumentException(obj.toString());
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    JsonObject requestObj = obj.get("request").getAsJsonObject();
    String op = requestObj.get("op").getAsString();
    switch (op) {
      case _subscribe:
        return gson.fromJson(obj, WebSocketSubscribeResponse.class);
      case _unsubscribe:
        return gson.fromJson(obj, WebSocketUnsubscribeResponse.class);
      case _authKeyExpires:
        return gson.fromJson(obj, WebSocketAuthKeyExpiresResponse.class);
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
