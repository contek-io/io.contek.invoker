package io.contek.invoker.ftx.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.IWebSocketMessageParser;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketKeys.type;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketSubscriptionKeys.subscribed;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketSubscriptionKeys.unsubscribed;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has(type)) {
      switch (obj.get(type).getAsString()) {
        case subscribed:
        case unsubscribed:
          return toSubscriptionMessage(obj);
      }
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionResponse.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
