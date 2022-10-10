package io.contek.invoker.binancedelivery.api.websocket.user;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancedelivery.api.websocket.user.constants.UserEventTypeKeys.*;

@ThreadSafe
public final class UserWebSocketParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

  static UserWebSocketParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  public WebSocketEventData fromText(String text) {
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

  private WebSocketEventData toUserData(JsonObject obj) {
    String eventType = obj.get("e").getAsString();
    return switch (eventType) {
      case _ACCOUNT_UPDATE -> gson.fromJson(obj, AccountUpdateChannel.Data.class);
      case _ORDER_TRADE_UPDATE -> gson.fromJson(obj, OrderUpdateChannel.Data.class);
      case _ACCOUNT_CONFIG_UPDATE -> gson.fromJson(obj, AccountConfigUpdateChannel.Data.class);
      case _MARGIN_CALL -> gson.fromJson(obj, MarginCallChannel.Data.class);
      case _listenKeyExpired -> gson.fromJson(obj, UserDataStreamExpiredEvent.class);
      default -> throw new IllegalStateException("Unrecognized event type: " + eventType);
    };
  }

  private UserWebSocketParser() {}

  @Immutable
  private static class InstanceHolder {

    private static final UserWebSocketParser INSTANCE = new UserWebSocketParser();

    private InstanceHolder() {}
  }
}
