package io.contek.invoker.bybit.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bybit.api.websocket.common.WebSocketRequestConfirmation;
import io.contek.invoker.bybit.api.websocket.market.OrderBook200Channel;
import io.contek.invoker.bybit.api.websocket.market.OrderBook25Channel;
import io.contek.invoker.bybit.api.websocket.market.OrderBookChannel;
import io.contek.invoker.bybit.api.websocket.market.TradeChannel;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketDataMessageTypeKeys.delta;
import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketDataMessageTypeKeys.snapshot;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("topic")) {
      return toTopicMessage(obj);
    }
    if (obj.has("request")) {
      return toRequestConfirmation(obj);
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toTopicMessage(JsonObject obj) {
    String topic = obj.get("topic").getAsString();
    if (topic.startsWith(OrderBook200Channel.TOPIC_PREFIX)
        || topic.startsWith(OrderBook25Channel.TOPIC_PREFIX)) {
      switch (obj.get("type").getAsString()) {
        case snapshot:
          return gson.fromJson(obj, OrderBookChannel.SnapshotMessage.class);
        case delta:
          return gson.fromJson(obj, OrderBookChannel.DeltaMessage.class);
        default:
          throw new IllegalStateException();
      }
    }
    if (topic.startsWith(TradeChannel.TOPIC_PREFIX)) {
      return gson.fromJson(obj, TradeChannel.Message.class);
    }
    throw new IllegalArgumentException(obj.toString());
  }

  private WebSocketRequestConfirmation toRequestConfirmation(JsonObject obj) {
    return gson.fromJson(obj, WebSocketRequestConfirmation.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();
  }
}
