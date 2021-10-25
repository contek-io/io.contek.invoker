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
import io.contek.invoker.deribit.api.websocket.market.ChartTradesChannel;
import io.contek.invoker.deribit.api.websocket.market.TickerChannel;
import io.contek.invoker.deribit.api.websocket.market.TradesChannel;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Nullable
  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);
    if (!json.isJsonObject()) {
      throw new IllegalArgumentException(text);
    }
    JsonObject obj = json.getAsJsonObject();
    if (obj.has("result")) {
      return toConfirmationMessage(obj);
    } else if (obj.has("params")) {
      return toDataMessage(obj);
    } else {
      throw new IllegalArgumentException(text);
    }
  }

  private WebSocketInboundMessage toConfirmationMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketResponse.class);
  }

  private WebSocketInboundMessage toDataMessage(JsonObject obj) {
    String instrumentName = obj.get("params").getAsJsonObject().get("channel").getAsString();
    if (instrumentName.startsWith(WebSocketChannelKeys._book)) {
      return gson.fromJson(obj, BookChannel.Message.class);
    } else if (instrumentName.startsWith(WebSocketChannelKeys._chart)) {
      return gson.fromJson(obj, ChartTradesChannel.Message.class);
    } else if (instrumentName.startsWith(WebSocketChannelKeys._ticker)) {
      return gson.fromJson(obj, TickerChannel.Message.class);
    } else if (instrumentName.startsWith(WebSocketChannelKeys._trades)) {
      return gson.fromJson(obj, TradesChannel.Message.class);
    } else {
      throw new IllegalArgumentException(obj.toString());
    }
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
