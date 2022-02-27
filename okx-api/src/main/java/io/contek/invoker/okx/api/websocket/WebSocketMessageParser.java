package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.okx.api.websocket.common.*;
import io.contek.invoker.okx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.okx.api.websocket.market.TickersChannel;
import io.contek.invoker.okx.api.websocket.market.TradesChannel;
import io.contek.invoker.okx.api.websocket.user.OrdersChannel;
import io.vertx.core.json.JsonObject;
import io.contek.invoker.okx.api.websocket.user.PositionsChannel;

import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys._login;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private WebSocketMessageParser() {}

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected WebSocketInboundMessage fromText(String text) {
    if (text.equals(_pong)) {
      return new WebSocketPong();
    }

    try {
      final JsonObject obj = new JsonObject(text);

      if (obj.containsKey(_event)) {
        return toEvent(obj);
      }

      if (obj.containsKey(_data)) {
        return toPushData(obj);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    throw new IllegalArgumentException(text);
  }

  private WebSocketEvent toEvent(JsonObject obj) {
    String event = obj.getString(_event);
    return switch (event) {
      case _subscribe, _unsubscribe -> toSubscriptionMessage(obj);
      case _login, _error -> toGeneralResponse(obj);
      default -> throw new IllegalArgumentException(event);
    };
  }

  private WebSocketSubscriptionResponse toSubscriptionMessage(JsonObject obj) {
    return obj.mapTo(WebSocketSubscriptionResponse.class);
  }

  private WebSocketGeneralResponse toGeneralResponse(JsonObject obj) {
    return obj.mapTo(WebSocketGeneralResponse.class);
  }

  private WebSocketChannelPushData<?> toPushData(JsonObject obj) {
    if (!obj.containsKey(_arg)) {
      throw new IllegalArgumentException(obj.toString());
    }

    JsonObject arg = obj.getJsonObject(_arg);
    String channel = arg.getString(_channel);

    return switch (channel) {
      case _books, _books5, _books50_l2_tbt, _books_l2_tbt -> obj.mapTo(OrderBookChannel.Message.class);
      case _trades -> obj.mapTo(TradesChannel.Message.class);
      case _tickers -> obj.mapTo(TickersChannel.Message.class);
      case _orders -> obj.mapTo(OrdersChannel.Message.class);
      case _positions -> obj.mapTo(PositionsChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
