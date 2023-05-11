package io.contek.invoker.okx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.okx.api.websocket.common.*;
import io.contek.invoker.okx.api.websocket.market.*;
import io.contek.invoker.okx.api.websocket.user.OrdersChannel;
import io.contek.invoker.okx.api.websocket.user.PositionsChannel;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys._login;

@Immutable
final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

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
    return switch (event) {
      case _subscribe, _unsubscribe -> toSubscriptionMessage(obj);
      case _login, _error -> toGeneralResponse(obj);
      default -> throw new IllegalArgumentException(event);
    };
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

    return switch (channel) {
      case _books, _books5, _books50_l2_tbt, _books_l2_tbt -> gson.fromJson(
          obj, OrderBookChannel.Message.class);
      case _trades -> gson.fromJson(obj, TradesChannel.Message.class);
      case _tickers -> gson.fromJson(obj, TickersChannel.Message.class);
      case _orders -> gson.fromJson(obj, OrdersChannel.Message.class);
      case _positions -> gson.fromJson(obj, PositionsChannel.Message.class);
      case _mark_price -> gson.fromJson(obj, MarkPriceChannel.Message.class);
      case _index_tickers -> gson.fromJson(obj, IndexTickersChannel.Message.class);
      default -> throw new IllegalArgumentException(obj.toString());
    };
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
