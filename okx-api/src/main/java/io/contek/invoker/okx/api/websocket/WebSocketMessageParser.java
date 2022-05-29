package io.contek.invoker.okx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.okx.api.websocket.common.*;
import io.contek.invoker.okx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.okx.api.websocket.market.TickersChannel;
import io.contek.invoker.okx.api.websocket.market.TradesChannel;
import io.contek.invoker.okx.api.websocket.user.OrdersChannel;

import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys._login;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

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

    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }

    JsonObject obj = json.getAsJsonObject();
    if (obj.has(_event)) {
      return toEvent(obj);
    }

    if (obj.has(_data)) {
      return toPushData(obj);
    }

    throw new IllegalArgumentException(text);
  }

  private WebSocketEvent toEvent(JsonObject obj) {
    String event = obj.get(_event).getAsString();
    switch (event) {
      case _subscribe:
      case _unsubscribe:
        return toSubscriptionMessage(obj);
      case _login:
      case _error:
        return toGeneralResponse(obj);
      default:
        throw new IllegalArgumentException(event);
    }
  }

  private WebSocketSubscriptionResponse toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketGeneralResponse toGeneralResponse(JsonObject obj) {
    return gson.fromJson(obj, WebSocketGeneralResponse.class);
  }

  private WebSocketChannelPushData<?> toPushData(JsonObject obj) {
    if (!obj.has(_arg)) {
      throw new IllegalArgumentException(obj.toString());
    }

    JsonObject arg = obj.getAsJsonObject(_arg);
    String channel = arg.get(_channel).getAsString();

    switch (channel) {
      case _books:
      case _books5:
      case _books50_l2_tbt:
      case _books_l2_tbt:
        return gson.fromJson(obj, OrderBookChannel.Message.class);
      case _trades:
        return gson.fromJson(obj, TradesChannel.Message.class);
      case _tickers:
        return gson.fromJson(obj, TickersChannel.Message.class);
      case _orders:
        return gson.fromJson(obj, OrdersChannel.Message.class);
      default:
        throw new IllegalArgumentException(obj.toString());
    }
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
