package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import com.google.common.io.CharStreams;
import io.contek.invoker.commons.buffer.BufferInputStream;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketBinaryMessageParser;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys.*;

final class NotificationWebSocketMessageParser extends WebSocketBinaryMessageParser {

  private final Map<String, Class<? extends NotificationWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof NotificationWebSocketChannel)) {
      return;
    }
      NotificationWebSocketChannel<?, ?> channel = (NotificationWebSocketChannel<?, ?>) component;
      channelMessageTypes.put(channel.getId().getTopic(), channel.getMessageType());
  }

  @Override
  protected String decode(io.vertx.core.buffer.Buffer binary) {
    try (Reader reader =
        new InputStreamReader(new GZIPInputStream(new BufferInputStream(binary)))) {
      return CharStreams.toString(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      parse(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private NotificationWebSocketInboundMessage parse(JsonObject obj) {
    String op = obj.getString("op");

    return switch (op) {
      case _sub, _unsub -> obj.mapTo(NotificationWebSocketConfirmation.class);
      case _ping -> obj.mapTo(NotificationWebSocketPing.class);
      case _notify -> toMarketDataMessage(obj);
      case _close -> obj.mapTo(NotificationWebSocketClose.class);
      default -> throw new UnsupportedOperationException(obj.toString());
    };
  }

  private NotificationWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    String topic = obj.getString("topic");
      Class<? extends NotificationWebSocketChannelMessage> type = channelMessageTypes.get(topic);
      return obj.mapTo(type);
  }
}
