package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.websocket.market.BookChangeChannel;
import io.contek.invoker.deribit.api.websocket.market.BookSnapshotChannel;
import io.contek.invoker.deribit.api.websocket.market.TradesChannel;
import io.contek.invoker.deribit.api.websocket.user.UserChangesChannel;
import io.contek.invoker.deribit.api.websocket.user.UserOrdersChannel;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private final Map<Integer, Class<? extends WebSocketResponse<?>>> pendingRequests =
      new HashMap<>();

  public void register(Integer id, Class<? extends WebSocketResponse<?>> type) {
    synchronized (pendingRequests) {
      pendingRequests.put(id, type);
    }
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    final JsonObject obj = new JsonObject(text);

    if (obj.containsKey("id")) {
      return toResponseMessage(obj);
    } else if (obj.containsKey("params")) {
      return toDataMessage(obj);
    } else {
      throw new IllegalArgumentException(text);
    }
  }

  private WebSocketResponse<?> toResponseMessage(JsonObject obj) {
    int id = obj.getInteger("id");
    Class<? extends WebSocketResponse<?>> type = pendingRequests.remove(id);
    if (type == null) {
      throw new IllegalStateException(format("Expected response type not found: %d", id));
    }
    return obj.mapTo(type);
  }

  private WebSocketInboundMessage toDataMessage(JsonObject obj) {
    JsonObject params = obj.getJsonObject("params");
    String channel = params.getString("channel");
    if (channel.startsWith(WebSocketChannelKeys._book)) {
      JsonObject data = params.getJsonObject("data");
      if (data.containsKey("type")) {
        return obj.mapTo(BookChangeChannel.Message.class);
      }
      return obj.mapTo(BookSnapshotChannel.Message.class);
    } else if (channel.startsWith(WebSocketChannelKeys._trades)) {
      return obj.mapTo(TradesChannel.Message.class);
    } else if (channel.startsWith(WebSocketChannelKeys._user_changes)) {
      return obj.mapTo(UserChangesChannel.Message.class);
    }
    if (channel.startsWith(WebSocketChannelKeys._user_orders)) {
      return obj.mapTo(UserOrdersChannel.Message.class);
    } else {
      throw new IllegalArgumentException(obj.toString());
    }
  }
}
