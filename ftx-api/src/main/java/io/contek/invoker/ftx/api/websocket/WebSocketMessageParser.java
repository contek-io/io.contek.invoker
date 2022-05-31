package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInfoMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketPong;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.ftx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.ftx.api.websocket.market.TickerChannel;
import io.contek.invoker.ftx.api.websocket.market.TradesChannel;
import io.contek.invoker.ftx.api.websocket.user.OrdersChannel;
import io.vertx.core.json.JsonObject;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys.*;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private WebSocketMessageParser() {
  }

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      JsonObject obj = new JsonObject(text);

      if (obj.isEmpty()) {
        throw new IllegalArgumentException(text);
      }

      if (!obj.containsKey(_type)) {
        throw new IllegalArgumentException(text);
      }

      return switch (obj.getString(_type)) {
        case _subscribed, _unsubscribed -> toSubscriptionMessage(obj);
        case _partial, _update -> toChannelMessage(obj);
        case _pong -> toPongMessage(obj);
        case _error, _info -> toInfoMessage(obj);
        default -> throw new IllegalArgumentException(text);
      };
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  private WebSocketPong toPongMessage(JsonObject obj) {
    return obj.mapTo(WebSocketPong.class);
  }

  private WebSocketInboundMessage toSubscriptionMessage(JsonObject obj) {
    return obj.mapTo(WebSocketSubscriptionResponse.class);
  }

  private WebSocketInboundMessage toChannelMessage(JsonObject obj) {
    if (!obj.containsKey(_channel)) {
      throw new IllegalArgumentException(obj.toString());
    }
    return switch (obj.getString(_channel)) {
      case _orderbook -> obj.mapTo(OrderBookChannel.Message.class);
      case _trades -> obj.mapTo(TradesChannel.Message.class);
      case _ticker -> obj.mapTo(TickerChannel.Message.class);
      case _orders -> obj.mapTo(OrdersChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketInfoMessage toInfoMessage(JsonObject obj) {
    return obj.mapTo(WebSocketInfoMessage.class);
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
