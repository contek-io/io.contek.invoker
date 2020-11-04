package io.contek.invoker.coinbasepro.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketSubscriptionMessage;
import io.contek.invoker.coinbasepro.api.websocket.market.Level2Channel;
import io.contek.invoker.coinbasepro.api.websocket.market.MatchesChannel;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.coinbasepro.api.websocket.common.constants.WebSocketMessageKeys.*;

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
    if (obj.has(_type)) {
      switch (obj.get(_type).getAsString()) {
        case _subscriptions:
          return toSubscriptionMessage(obj);
        case _snapshot:
          return toSnapshotMessage(obj);
        case _l2update:
          return toL2UpdateMessage(obj);
        case _match:
        case _last_match:
          return toMatchMessage(obj);
      }
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toSubscriptionMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketSubscriptionMessage.class);
  }

  private AnyWebSocketMessage toSnapshotMessage(JsonObject obj) {
    return gson.fromJson(obj, Level2Channel.SnapshotMessage.class);
  }

  private AnyWebSocketMessage toL2UpdateMessage(JsonObject obj) {
    return gson.fromJson(obj, Level2Channel.L2UpdateMessage.class);
  }

  private AnyWebSocketMessage toMatchMessage(JsonObject obj) {
    return gson.fromJson(obj, MatchesChannel.Message.class);
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
