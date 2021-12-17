package io.contek.invoker.bybitlinear.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.bybitlinear.api.websocket.market.OrderBookChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.bybitlinear.api.websocket.common.constants.WebSocketDataMessageTypeKeys._delta;
import static io.contek.invoker.bybitlinear.api.websocket.common.constants.WebSocketDataMessageTypeKeys._snapshot;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  private final Map<String, Class<? extends WebSocketTopicMessage>> channelMessageTypes =
      new HashMap<>();

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof WebSocketChannel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      WebSocketChannel<?, ?> channel = (WebSocketChannel<?, ?>) component;
      channelMessageTypes.put(channel.getId().getTopic(), channel.getMessageType());
    }
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("request")) {
      return toOperationResponse(obj);
    }
    if (obj.has("topic")) {
      return toTopicMessage(obj);
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toTopicMessage(JsonObject obj) {
    String topic = obj.get("topic").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends WebSocketTopicMessage> type = channelMessageTypes.get(topic);
      if (type == null) {
        throw new IllegalArgumentException(topic);
      }

      if (type.equals(OrderBookChannel.Message.class)) {
        switch (obj.get("type").getAsString()) {
          case _snapshot:
            return gson.fromJson(obj, OrderBookChannel.SnapshotMessage.class);
          case _delta:
            return gson.fromJson(obj, OrderBookChannel.DeltaMessage.class);
          default:
            throw new IllegalStateException();
        }
      }

      return gson.fromJson(obj, type);
    }
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    return gson.fromJson(obj, WebSocketOperationResponse.class);
  }
}
