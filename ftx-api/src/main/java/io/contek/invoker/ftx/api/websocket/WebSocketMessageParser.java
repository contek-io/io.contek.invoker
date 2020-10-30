package io.contek.invoker.ftx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.api.websocket.IWebSocketMessageParser;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.ftx.api.websocket.market.OrderBookChannel;
import io.contek.invoker.ftx.api.websocket.market.TradesChannel;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys.*;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys.*;

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
    if (!obj.has(type)) {
      throw new IllegalArgumentException(text);
    }
    switch (obj.get(type).getAsString()) {
      case subscribed:
      case unsubscribed:
        return toSubscriptionMessage(obj);
      case partial:
      case update:
        return toChannelMessage(obj);
      default:
        throw new IllegalArgumentException(text);
    }
  }

  private WebSocketInboundMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketInboundMessage toChannelMessage(JsonObject obj) {
    if (!obj.has(channel)) {
      throw new IllegalArgumentException(obj.toString());
    }
    switch (obj.get(channel).getAsString()) {
      case orderbook:
        return gson.fromJson(obj, OrderBookChannel.Message.class);
      case trades:
        return gson.fromJson(obj, TradesChannel.Message.class);
      default:
        throw new IllegalArgumentException(obj.toString());
    }
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
