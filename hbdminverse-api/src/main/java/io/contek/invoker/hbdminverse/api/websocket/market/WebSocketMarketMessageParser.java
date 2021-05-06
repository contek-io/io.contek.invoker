package io.contek.invoker.hbdminverse.api.websocket.market;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketPing;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketUnsubscribeConfirmation;

import javax.annotation.concurrent.ThreadSafe;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@ThreadSafe
final class WebSocketMarketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  private final Map<String, Class<? extends WebSocketMarketDataMessage>> channelMessageTypes =
      new HashMap<>();

  WebSocketMarketMessageParser() {}

  void register(String channel, Class<? extends WebSocketMarketDataMessage> type) {
    synchronized (channelMessageTypes) {
      channelMessageTypes.put(channel, type);
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
      return gson.fromJson(obj, WebSocketSubscribeConfirmation.class);
    }

    if (obj.has("unsubbed")) {
      return gson.fromJson(obj, WebSocketUnsubscribeConfirmation.class);
    }

    throw new UnsupportedOperationException(json.toString());
  }

  private WebSocketMarketDataMessage toMarketDataMessage(JsonObject obj) {
    String ch = obj.get("ch").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends WebSocketMarketDataMessage> type = channelMessageTypes.get(ch);
      return gson.fromJson(obj, type);
    }
  }
}
