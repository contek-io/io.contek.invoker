package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketPing;
import io.contek.invoker.hbdmlinear.api.websocket.market.MarketWebSocketChannelMessage;
import io.contek.invoker.hbdmlinear.api.websocket.user.LiquidationOrderChannel;

import javax.annotation.concurrent.Immutable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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

  private AnyWebSocketMessage parse(JsonElement json) {
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(json.toString());
    }

    System.out.println(json.toString());
    if (true) {
      return new LiquidationOrderChannel.Message();
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("ch")) {
      return toMarketDataMessage(obj);
    }

    if (obj.has("ping")) {
      return gson.fromJson(obj, WebSocketPing.class);
    }

    throw new UnsupportedOperationException(json.toString());
  }

  private MarketWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    String ch = obj.get("ch").getAsString();
    return null;
  }
}
