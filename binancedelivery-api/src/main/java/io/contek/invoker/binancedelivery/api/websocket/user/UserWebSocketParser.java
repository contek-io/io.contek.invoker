package io.contek.invoker.binancedelivery.api.websocket.user;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketParser implements IWebSocketMessageParser {

  private final Gson gson = GsonFactory.buildGson();

  static UserWebSocketParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public WebSocketEventMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }

    JsonObject obj = json.getAsJsonObject();
    if (obj.has("e")) {
      return toUserData(obj);
    }

    throw new IllegalStateException(text);
  }

  private WebSocketEventMessage toUserData(JsonObject obj) {
    String eventType = obj.get("e").getAsString();
    switch (eventType) {
      case "ACCOUNT_UPDATE":
        return gson.fromJson(obj, AccountUpdateChannel.Message.class);
      case "ORDER_TRADE_UPDATE":
        return gson.fromJson(obj, OrderUpdateChannel.Message.class);
      case "ACCOUNT_CONFIG_UPDATE":
        return gson.fromJson(obj, AccountConfigUpdateChannel.Message.class);
      case "MARGIN_CALL":
        return gson.fromJson(obj, MarginCallChannel.Message.class);
      case "listenKeyExpired":
        return gson.fromJson(obj, UserDataStreamExpiredEvent.class);
      default:
        throw new IllegalStateException("Unrecognized event type: " + eventType);
    }
  }

  private UserWebSocketParser() {}

  @Immutable
  private static class InstanceHolder {

    private static final UserWebSocketParser INSTANCE = new UserWebSocketParser();

    private InstanceHolder() {}
  }
}
