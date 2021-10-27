package io.contek.invoker.deribit.api.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.websocket.market.BookChannel;
import io.contek.invoker.deribit.api.websocket.market.TradesChannel;
import io.contek.invoker.deribit.api.websocket.user.UserOrdersChannel;

import javax.annotation.concurrent.Immutable;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  private final Map<Integer, Class<? extends WebSocketResponse<?>>> pendingRequests =
      new HashMap<>();

  public void register(Integer id, Class<? extends WebSocketResponse<?>> type) {
    synchronized (pendingRequests) {
      pendingRequests.put(id, type);
    }
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("id")) {
      return toResponseMessage(obj);
    } else if (obj.has("params")) {
      return toDataMessage(obj);
    } else {
      throw new IllegalArgumentException(text);
    }
  }

  private WebSocketResponse<?> toResponseMessage(JsonObject obj) {
    int id = obj.get("id").getAsInt();
    Class<? extends WebSocketResponse<?>> type = pendingRequests.remove(id);
    if (type == null) {
      throw new IllegalStateException(format("Expected response type not found: %d", id));
    }
    return gson.fromJson(obj, type);
  }

  private WebSocketInboundMessage toDataMessage(JsonObject obj) {
    String channel = obj.get("params").getAsJsonObject().get("channel").getAsString();
    if (channel.startsWith(WebSocketChannelKeys._book)) {
      return gson.fromJson(obj, BookChannel.Message.class);
    } else if (channel.startsWith(WebSocketChannelKeys._trades)) {
      return gson.fromJson(obj, TradesChannel.Message.class);
    } else if (channel.startsWith(WebSocketChannelKeys._user_orders)) {
      return gson.fromJson(obj, UserOrdersChannel.Message.class);
    } else {
      throw new IllegalArgumentException(obj.toString());
    }
  }
}
