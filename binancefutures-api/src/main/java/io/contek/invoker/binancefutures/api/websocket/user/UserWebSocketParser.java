package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys.*;

public final class UserWebSocketParser extends WebSocketTextMessageParser {

  static UserWebSocketParser getInstance() {
    return UserWebSocketParser.InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  public WebSocketEventMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);

      if (obj.containsKey("e")) {
        return toUserData(obj);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private WebSocketEventMessage toUserData(JsonObject obj) {
    String eventType = obj.getString("e");
    return switch (eventType) {
      case _ACCOUNT_UPDATE -> obj.mapTo(AccountUpdateChannel.Message.class);
      case _ORDER_TRADE_UPDATE -> obj.mapTo(OrderUpdateChannel.Message.class);
      case _ACCOUNT_CONFIG_UPDATE -> obj.mapTo(AccountConfigUpdateChannel.Message.class);
      case _MARGIN_CALL -> obj.mapTo(MarginCallChannel.Message.class);
      case _listenKeyExpired -> obj.mapTo(UserDataStreamExpiredEvent.class);
      default -> throw new IllegalStateException("Unrecognized event type: " + eventType);
    };
  }

  private UserWebSocketParser() {}

  private static class InstanceHolder {

    private static final UserWebSocketParser INSTANCE = new UserWebSocketParser();

    private InstanceHolder() {}
  }
}
