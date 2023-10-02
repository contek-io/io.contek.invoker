package io.contek.invoker.bybit.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.bybit.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

  private final Map<String, Class<? extends WebSocketTopicMessage<?>>> channelMessageTypes =
      new HashMap<>();

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof WebSocketChannel<?> channel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      channelMessageTypes.put(channel.getId().getTopic(), channel.getMessageType());
    }
  }

  @Override
  protected AnyWebSocketMessage fromText(String text) {
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
      Class<? extends WebSocketTopicMessage<?>> type = channelMessageTypes.get(topic);
      if (type == null) {
        throw new IllegalArgumentException(topic);
      }

      return gson.fromJson(obj, type);
    }
  }

  private WebSocketOperationResponse toOperationResponse(JsonObject obj) {
    return gson.fromJson(obj, WebSocketOperationResponse.class);
  }
}
