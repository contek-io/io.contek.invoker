package io.contek.invoker.binancedelivery.api.websocket.user;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

public final class UserWebSocketParser extends WebSocketTextMessageParser {

  private UserWebSocketParser() {}

  static UserWebSocketParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  public WebSocketEventMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      if (obj.isEmpty()) {
        throw new IllegalArgumentException(text);
      }

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
      case "ACCOUNT_UPDATE" -> obj.mapTo(AccountUpdateChannel.Message.class);
      case "ORDER_TRADE_UPDATE" -> obj.mapTo(OrderUpdateChannel.Message.class);
      case "ACCOUNT_CONFIG_UPDATE" -> obj.mapTo(AccountConfigUpdateChannel.Message.class);
      case "MARGIN_CALL" -> obj.mapTo(MarginCallChannel.Message.class);
      case "listenKeyExpired" -> obj.mapTo(UserDataStreamExpiredEvent.class);
      default -> throw new IllegalStateException("Unrecognized event type: " + eventType);
    };
  }

  private static class InstanceHolder {

    private static final UserWebSocketParser INSTANCE = new UserWebSocketParser();

    private InstanceHolder() {}
  }
}
