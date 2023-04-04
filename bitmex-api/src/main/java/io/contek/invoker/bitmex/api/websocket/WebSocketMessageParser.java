package io.contek.invoker.bitmex.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bitmex.api.websocket.common.*;
import io.contek.invoker.bitmex.api.websocket.market.*;
import io.contek.invoker.bitmex.api.websocket.user.ExecutionChannel;
import io.contek.invoker.bitmex.api.websocket.user.OrderChannel;
import io.contek.invoker.bitmex.api.websocket.user.PositionChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys.*;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketTableKeys.*;
import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;

@Immutable
final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    if (text.equals(_pong)) {
      return new WebSocketPong();
    }

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
    return switch (tableName) {
      case _orderBookL2 -> gson.fromJson(obj, OrderBookL2Channel.Message.class);
      case _quote -> gson.fromJson(obj, QuoteChannel.Message.class);
      case _trade -> gson.fromJson(obj, TradeChannel.Message.class);
      case _instrument -> gson.fromJson(obj, InstrumentChannel.Message.class);
      case _tradeBin1m, _tradeBin5m, _tradeBin1h, _tradeBin1d -> gson.fromJson(
          obj, TradeBinChannel.Message.class);
      case _liquidation -> gson.fromJson(obj, LiquidationChannel.Message.class);
      case _execution -> gson.fromJson(obj, ExecutionChannel.Message.class);
      case _order -> gson.fromJson(obj, OrderChannel.Message.class);
      case _position -> gson.fromJson(obj, PositionChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    JsonObject requestObj = obj.get("request").getAsJsonObject();
    String op = requestObj.get("op").getAsString();
    return switch (op) {
      case _subscribe -> gson.fromJson(obj, WebSocketSubscribeResponse.class);
      case _unsubscribe -> gson.fromJson(obj, WebSocketUnsubscribeResponse.class);
      case _authKeyExpires -> gson.fromJson(obj, WebSocketAuthKeyExpiresResponse.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
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
