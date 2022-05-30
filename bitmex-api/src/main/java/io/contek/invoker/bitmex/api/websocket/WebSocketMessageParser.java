package io.contek.invoker.bitmex.api.websocket;

import io.contek.invoker.bitmex.api.websocket.common.*;
import io.contek.invoker.bitmex.api.websocket.market.*;
import io.contek.invoker.bitmex.api.websocket.user.OrderChannel;
import io.contek.invoker.bitmex.api.websocket.user.PositionChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys.*;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketTableKeys.*;
import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private WebSocketMessageParser() {}

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

    try {
      final JsonObject obj = new JsonObject(text);

      if (obj.containsKey("table")) {
        return toTableMessage(obj);
      }
      if (obj.containsKey("request")) {
        return toOperationResponse(obj);
      }
      if (obj.containsKey("info")) {
        return toInfo(obj);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private WebSocketTableDataMessage<?> toTableMessage(JsonObject obj) {
    String tableName = obj.getString("table");
    return switch (tableName) {
      case _orderBookL2 -> obj.mapTo(OrderBookL2Channel.Message.class);
      case _quote -> obj.mapTo(QuoteChannel.Message.class);
      case _trade -> obj.mapTo(TradeChannel.Message.class);
      case _instrument -> obj.mapTo(InstrumentChannel.Message.class);
      case _tradeBin1m, _tradeBin5m, _tradeBin1h, _tradeBin1d -> obj.mapTo(TradeBinChannel.Message.class);
      case _liquidation -> obj.mapTo(LiquidationChannel.Message.class);
      case _order -> obj.mapTo(OrderChannel.Message.class);
      case _position -> obj.mapTo(PositionChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    JsonObject requestObj = obj.getJsonObject("request");
    String op = requestObj.getString("op");
    return switch (op) {
      case _subscribe -> obj.mapTo(WebSocketSubscribeResponse.class);
      case _unsubscribe -> obj.mapTo(WebSocketUnsubscribeResponse.class);
      case _authKeyExpires -> obj.mapTo(WebSocketAuthKeyExpiresResponse.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketInfo toInfo(JsonObject obj) {
    return obj.mapTo(WebSocketInfo.class);
  }

  private static class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
