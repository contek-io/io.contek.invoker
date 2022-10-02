package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketBinaryMessageParser;

import javax.annotation.concurrent.Immutable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys.*;

@Immutable
final class NotificationWebSocketMessageParser extends WebSocketBinaryMessageParser {

  private final Gson gson = GsonFactory.buildGson();

  private final Map<String, Class<? extends NotificationWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof NotificationWebSocketChannel<?, ?> channel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      channelMessageTypes.put(channel.getId().getTopic(), channel.getMessageType());
    }
  }

  @Override
  protected String decode(byte[] bytes) {
    try (Reader reader =
        new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)))) {
      return CharStreams.toString(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    return parse(json);
  }

  private NotificationWebSocketInboundMessage parse(JsonElement json) {
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(json.toString());
    }

    JsonObject obj = json.getAsJsonObject();
    String op = obj.get("op").getAsString();

    return switch (op) {
      case _notify -> toMarketDataMessage(obj);
      case _ping -> gson.fromJson(obj, NotificationWebSocketPing.class);
      case _sub, _unsub -> gson.fromJson(obj, NotificationWebSocketConfirmation.class);
      case _close -> gson.fromJson(obj, NotificationWebSocketClose.class);
      default -> throw new UnsupportedOperationException(json.toString());
    };
  }

  private NotificationWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    String topic = obj.get("topic").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends NotificationWebSocketChannelMessage> type = channelMessageTypes.get(topic);
      return gson.fromJson(obj, type);
    }
  }
}
