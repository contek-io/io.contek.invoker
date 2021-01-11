package io.contek.invoker.deribit.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInfoMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketInboundKeys;
import io.contek.invoker.deribit.api.websocket.market.OrderBookChannel;
import io.contek.invoker.deribit.api.websocket.market.TickerChannel;
import io.contek.invoker.deribit.api.websocket.market.TradesChannel;

import javax.annotation.concurrent.Immutable;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

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
    if (!obj.has(WebSocketInboundKeys._type)) {
      throw new IllegalArgumentException(text);
    }
    switch (obj.get(WebSocketInboundKeys._type).getAsString()) {
      case WebSocketInboundKeys._subscribed:
      case WebSocketInboundKeys._unsubscribed:
        return toSubscriptionMessage(obj);
      case WebSocketInboundKeys._partial:
      case WebSocketInboundKeys._update:
        return toChannelMessage(obj);
      case WebSocketInboundKeys._error:
      case WebSocketInboundKeys._info:
        return toInfoMessage(obj);
      default:
        throw new IllegalArgumentException(text);
    }
  }

  private WebSocketInboundMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketInboundMessage toChannelMessage(JsonObject obj) {
    if (!obj.has(WebSocketChannelKeys._channel)) {
      throw new IllegalArgumentException(obj.toString());
    }
    switch (obj.get(WebSocketChannelKeys._channel).getAsString()) {
      case WebSocketChannelKeys._orderbook:
        return gson.fromJson(obj, OrderBookChannel.Message.class);
      case WebSocketChannelKeys._trades:
        return gson.fromJson(obj, TradesChannel.Message.class);
      case WebSocketChannelKeys._ticker:
        return gson.fromJson(obj, TickerChannel.Message.class);
      default:
        throw new IllegalArgumentException(obj.toString());
    }
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
