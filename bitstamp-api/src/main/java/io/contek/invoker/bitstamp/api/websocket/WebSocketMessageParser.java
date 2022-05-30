package io.contek.invoker.bitstamp.api.websocket;

import io.contek.invoker.bitstamp.api.websocket.common.WebSocketRequestConfirmationMessage;
import io.contek.invoker.bitstamp.api.websocket.market.DiffOrderBookChannel;
import io.contek.invoker.bitstamp.api.websocket.market.LiveTradesChannel;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.vertx.core.json.JsonObject;

import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketEventKeys.*;
import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketFieldKeys._channel;
import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketFieldKeys._event;

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

      if (obj.containsKey(_event) && obj.containsKey(_channel)) {
        String eventValue = obj.getString(_event);
        String channelValue = obj.getString(_channel);
        if (eventValue.startsWith(_bts)) {
          return toRequestConfirmationMessage(obj);
        }
        if (eventValue.equals(_trade)) {
          if (channelValue.startsWith(LiveTradesChannel.PREFIX)) {
            return toLiveTradesMessage(obj);
          }
        }
        if (eventValue.equals(_data)) {
          if (channelValue.startsWith(DiffOrderBookChannel.PREFIX)) {
            return toDiffOrderBookMessage(obj);
          }
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    throw new IllegalArgumentException(text);
  }

  private WebSocketRequestConfirmationMessage toRequestConfirmationMessage(JsonObject obj) {
    return obj.mapTo(WebSocketRequestConfirmationMessage.class);
  }

  private DiffOrderBookChannel.Message toDiffOrderBookMessage(JsonObject obj) {
    return obj.mapTo(DiffOrderBookChannel.Message.class);
  }

  private LiveTradesChannel.Message toLiveTradesMessage(JsonObject obj) {
    return obj.mapTo(LiveTradesChannel.Message.class);
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
