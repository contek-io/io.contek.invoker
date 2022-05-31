package io.contek.invoker.binancespot.api.websocket.user;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

public final class UserWebSocketParser extends WebSocketTextMessageParser {

  private UserWebSocketParser() {}

  static UserWebSocketParser getInstance() {
    return UserWebSocketParser.InstanceHolder.INSTANCE;
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
    switch (eventType) {
      default -> throw new IllegalStateException("Unrecognized event type: " + eventType);
    }
  }

  private static class InstanceHolder {

    private static final UserWebSocketParser INSTANCE = new UserWebSocketParser();

    private InstanceHolder() {}
  }
}
