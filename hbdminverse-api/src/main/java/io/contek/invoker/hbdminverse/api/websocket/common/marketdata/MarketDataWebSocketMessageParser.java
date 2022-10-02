package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketBinaryMessageParser;

import javax.annotation.concurrent.ThreadSafe;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@ThreadSafe
final class MarketDataWebSocketMessageParser extends WebSocketBinaryMessageParser {

  private final Gson gson = GsonFactory.buildGson();

  private final Map<String, Class<? extends MarketDataWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  MarketDataWebSocketMessageParser() {}

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof MarketDataMarketWebSocketChannel<?, ?> channel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      channelMessageTypes.put(channel.getId().getChannel(), channel.getMessageType());
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

  private AnyWebSocketMessage parse(JsonElement json) {
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(json.toString());
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("ch")) {
      return toMarketDataMessage(obj);
    }

    if (obj.has("ping")) {
      return gson.fromJson(obj, MarketDataWebSocketPing.class);
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
