package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketPing;

import javax.annotation.concurrent.ThreadSafe;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@ThreadSafe
public final class MarketDataWebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = GsonFactory.buildGson();

  private final Map<String, Class<? extends MarketDataWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  public MarketDataWebSocketMessageParser() {}

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof MarketDataMarketWebSocketChannel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      MarketDataMarketWebSocketChannel<?, ?> channel =
          (MarketDataMarketWebSocketChannel<?, ?>) component;
      channelMessageTypes.put(channel.getId().getChannel(), channel.getMessageType());
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
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("ch")) {
      return toMarketDataMessage(obj);
    }

    if (obj.has("ping")) {
      return gson.fromJson(obj, WebSocketPing.class);
    }

    if (obj.has("subbed")) {
      return gson.fromJson(obj, MarketDataWebSocketSubscribeConfirmation.class);
    }

    if (obj.has("unsubbed")) {
      return gson.fromJson(obj, MarketDataWebSocketUnsubscribeConfirmation.class);
    }

    throw new UnsupportedOperationException(json.toString());
  }

  private MarketDataWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    String ch = obj.get("ch").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends MarketDataWebSocketChannelMessage> type = channelMessageTypes.get(ch);
      return gson.fromJson(obj, type);
    }
  }
}
