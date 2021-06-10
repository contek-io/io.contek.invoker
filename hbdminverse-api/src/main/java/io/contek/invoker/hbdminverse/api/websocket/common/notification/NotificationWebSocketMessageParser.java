package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static io.contek.invoker.hbdminverse.api.websocket.user.constants.OpKeys.*;

@Immutable
final class NotificationWebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  private final Map<String, Class<? extends NotificationWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof NotificationWebSocketChannel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      NotificationWebSocketChannel<?, ?> channel = (NotificationWebSocketChannel<?, ?>) component;
      channelMessageTypes.put(channel.getId().getTopic(), channel.getMessageType());
    }
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    return parse(json);
  }

  @Override
  public AnyWebSocketMessage parse(byte[] bytes) {
    try (Reader reader =
        new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)))) {
      JsonElement json = gson.fromJson(reader, JsonElement.class);
      return parse(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private NotificationWebSocketInboundMessage parse(JsonElement json) {
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(json.toString());
    }

    JsonObject obj = json.getAsJsonObject();
    String op = obj.get("op").getAsString();

    switch (op) {
      case _sub:
      case _unsub:
        return gson.fromJson(obj, NotificationWebSocketConfirmation.class);
      case _ping:
        return gson.fromJson(obj, NotificationWebSocketPing.class);
      case _notify:
        return toMarketDataMessage(obj);
      default:
    }

    throw new UnsupportedOperationException(json.toString());
  }

  private NotificationWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    String topic = obj.get("topic").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends NotificationWebSocketChannelMessage> type = channelMessageTypes.get(topic);
      return gson.fromJson(obj, type);
    }
  }
}
