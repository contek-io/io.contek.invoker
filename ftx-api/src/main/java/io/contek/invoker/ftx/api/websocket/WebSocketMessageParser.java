package io.contek.invoker.ftx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInfoMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketPong;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.ftx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.ftx.api.websocket.market.TickerChannel;
import io.contek.invoker.ftx.api.websocket.market.TradesChannel;
import io.contek.invoker.ftx.api.websocket.user.FillUpdateChannel;
import io.contek.invoker.ftx.api.websocket.user.OrderUpdateChannel;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys.*;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  private WebSocketMessageParser() {}

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public WebSocketInboundMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (!obj.has(_type)) {
      throw new IllegalArgumentException(text);
    }
    switch (obj.get(_type).getAsString()) {
      case _subscribed:
      case _unsubscribed:
        return toSubscriptionMessage(obj);
      case _partial:
      case _update:
        return toChannelMessage(obj);
      case _error:
      case _info:
        return toInfoMessage(obj);
      case _pong:
        return toPongMessage(obj);
      default:
        throw new IllegalArgumentException(text);
    }
  }

  private WebSocketInboundMessage toPongMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketPong.class);
  }

  private WebSocketInboundMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketInboundMessage toChannelMessage(JsonObject obj) {
    if (!obj.has(_channel)) {
      throw new IllegalArgumentException(obj.toString());
    }
    switch (obj.get(_channel).getAsString()) {
      case _orderbook:
        return gson.fromJson(obj, OrderBookChannel.Message.class);
      case _trades:
        return gson.fromJson(obj, TradesChannel.Message.class);
      case _ticker:
        return gson.fromJson(obj, TickerChannel.Message.class);
      case _orders:
        return gson.fromJson(obj, OrderUpdateChannel.Message.class);
      case _fills:
        return gson.fromJson(obj, FillUpdateChannel.Message.class);
      default:
        throw new IllegalArgumentException(obj.toString());
    }
  }

  private WebSocketInfoMessage toInfoMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketInfoMessage.class);
  }

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
