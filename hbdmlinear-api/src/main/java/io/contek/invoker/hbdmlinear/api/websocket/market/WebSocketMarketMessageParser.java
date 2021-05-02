package io.contek.invoker.hbdmlinear.api.websocket.market;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeConfirmation;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

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
  public WebSocketInboundMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("ch")) {
      return toMarketDataMessage(obj);
    }

    if (obj.has("subbed")) {
      return gson.fromJson(obj, WebSocketSubscribeConfirmation.class);
    }

    if (obj.has("unsubbed")) {
      return gson.fromJson(obj, WebSocketUnsubscribeConfirmation.class);
    }

    throw new UnsupportedOperationException(text);
  }

  private WebSocketMarketDataMessage toMarketDataMessage(JsonObject obj) {
    String ch = obj.get("ch").getAsString();
    synchronized (channelMessageTypes) {
      Class<? extends WebSocketMarketDataMessage> type = channelMessageTypes.get(ch);
      return gson.fromJson(obj, type);
    }
  }
}
