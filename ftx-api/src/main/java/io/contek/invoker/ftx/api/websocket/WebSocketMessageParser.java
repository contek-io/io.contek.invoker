package io.contek.invoker.ftx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInfoMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.ftx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.ftx.api.websocket.market.TickerChannel;
import io.contek.invoker.ftx.api.websocket.market.TradesChannel;
import io.contek.invoker.ftx.api.websocket.user.FillsChannel;
import io.contek.invoker.ftx.api.websocket.user.OrdersChannel;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys.*;

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
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (!obj.has(_type)) {
      throw new IllegalArgumentException(text);
    }
    return switch (obj.get(_type).getAsString()) {
      case _subscribed, _unsubscribed -> toSubscriptionMessage(obj);
      case _partial, _update -> toChannelMessage(obj);
      case _error, _info -> toInfoMessage(obj);
      default -> throw new IllegalArgumentException(text);
    };
  }

  private WebSocketInboundMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketInboundMessage toChannelMessage(JsonObject obj) {
    if (!obj.has(_channel)) {
      throw new IllegalArgumentException(obj.toString());
    }
    return switch (obj.get(_channel).getAsString()) {
      case _orderbook -> gson.fromJson(obj, OrderBookChannel.Message.class);
      case _trades -> gson.fromJson(obj, TradesChannel.Message.class);
      case _ticker -> gson.fromJson(obj, TickerChannel.Message.class);
      case _fills -> gson.fromJson(obj, FillsChannel.Message.class);
      case _orders -> gson.fromJson(obj, OrdersChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketInfoMessage toInfoMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketInfoMessage.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
