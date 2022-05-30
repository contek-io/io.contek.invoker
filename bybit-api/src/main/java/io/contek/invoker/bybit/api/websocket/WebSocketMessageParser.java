package io.contek.invoker.bybit.api.websocket;

import io.contek.invoker.bybit.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.bybit.api.websocket.market.OrderBookChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketDataMessageTypeKeys._delta;
import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketDataMessageTypeKeys._snapshot;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

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
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      if (obj.isEmpty()) {
        throw new IllegalArgumentException(text);
      }

      if (obj.containsKey("request")) {
        return toOperationResponse(obj);
      }
      if (obj.containsKey("topic")) {
        return toTopicMessage(obj);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toTopicMessage(JsonObject obj) {
    String topic = obj.getString("topic");
    synchronized (channelMessageTypes) {
      Class<? extends WebSocketTopicMessage> type = channelMessageTypes.get(topic);
      if (type == null) {
        throw new IllegalArgumentException(topic);
      }

      if (type.equals(OrderBookChannel.Message.class)) {
        return switch (obj.getString("type")) {
          case _snapshot -> obj.mapTo(OrderBookChannel.SnapshotMessage.class);
          case _delta -> obj.mapTo(OrderBookChannel.DeltaMessage.class);
          default -> throw new IllegalStateException();
        };
      }

      return obj.mapTo(type);
    }
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    return obj.mapTo(WebSocketOperationResponse.class);
  }
}
