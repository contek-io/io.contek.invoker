package io.contek.invoker.coinbasepro.api.websocket;

import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketSubscriptionMessage;
import io.contek.invoker.coinbasepro.api.websocket.market.Level2Channel;
import io.contek.invoker.coinbasepro.api.websocket.market.MatchesChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

import static io.contek.invoker.coinbasepro.api.websocket.common.constants.WebSocketMessageKeys.*;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private WebSocketMessageParser() {}

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      if (obj.isEmpty()) {
        throw new IllegalArgumentException(text);
      }

      if (obj.containsKey(_type)) {
        return switch (obj.getString(_type)) {
          case _subscriptions -> toSubscriptionMessage(obj);
          case _snapshot -> toSnapshotMessage(obj);
          case _l2update -> toL2UpdateMessage(obj);
          case _match, _last_match -> toMatchMessage(obj);
          default -> throw new IllegalStateException("Unexpected value: " + obj.getString(_type));
        };
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private AnyWebSocketMessage toSubscriptionMessage(JsonObject obj) {
    return obj.mapTo(WebSocketSubscriptionMessage.class);
  }

  private AnyWebSocketMessage toSnapshotMessage(JsonObject obj) {
    return obj.mapTo(Level2Channel.SnapshotMessage.class);
  }

  private AnyWebSocketMessage toL2UpdateMessage(JsonObject obj) {
    return obj.mapTo(Level2Channel.L2UpdateMessage.class);
  }

  private AnyWebSocketMessage toMatchMessage(JsonObject obj) {
    return obj.mapTo(MatchesChannel.Message.class);
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
