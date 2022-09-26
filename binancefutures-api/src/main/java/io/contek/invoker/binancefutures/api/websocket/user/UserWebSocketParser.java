package io.contek.invoker.binancefutures.api.websocket.user;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys.*;

@ThreadSafe
public final class UserWebSocketParser extends WebSocketTextMessageParser {

  private final Gson gson = new Gson();

  static UserWebSocketParser getInstance() {
    return UserWebSocketParser.InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  public WebSocketEventMessage fromText(String text) {
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
    return switch (eventType) {
      case _ACCOUNT_UPDATE -> gson.fromJson(obj, AccountUpdateChannel.Message.class);
      case _ORDER_TRADE_UPDATE -> gson.fromJson(obj, OrderUpdateChannel.Message.class);
      case _ACCOUNT_CONFIG_UPDATE -> gson.fromJson(obj, AccountConfigUpdateChannel.Message.class);
      case _MARGIN_CALL -> gson.fromJson(obj, MarginCallChannel.Message.class);
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
