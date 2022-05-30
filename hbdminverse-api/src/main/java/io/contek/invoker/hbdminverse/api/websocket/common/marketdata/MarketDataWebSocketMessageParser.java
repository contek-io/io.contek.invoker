package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import com.google.common.io.CharStreams;
import io.contek.invoker.commons.buffer.BufferInputStream;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketBinaryMessageParser;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketPing;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public final class MarketDataWebSocketMessageParser extends WebSocketBinaryMessageParser {

  private final Map<String, Class<? extends MarketDataWebSocketChannelMessage>>
      channelMessageTypes = new HashMap<>();

  public MarketDataWebSocketMessageParser() {}

  @Override
  public void register(IWebSocketComponent component) {
    if (!(component instanceof MarketDataMarketWebSocketChannel)) {
      return;
    }
    synchronized (channelMessageTypes) {
      MarketDataMarketWebSocketChannel<?, ?> channel =
          (MarketDataMarketWebSocketChannel<?, ?>) component;
      channelMessageTypes.put(channel.getId().getChannel(), channel.getMessageType());
    }
  }

  @Override
  protected String decode(io.vertx.core.buffer.Buffer binary) {
    try (Reader reader =
        new InputStreamReader(new GZIPInputStream(new BufferInputStream(binary)))) {
      return CharStreams.toString(reader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      return parse(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private AnyWebSocketMessage parse(JsonObject obj) {
    if (obj.containsKey("ch")) {
      return toMarketDataMessage(obj);
    }

    if (obj.containsKey("ping")) {
      return obj.mapTo(WebSocketPing.class);
    }

    if (obj.containsKey("subbed")) {
      return obj.mapTo(MarketDataWebSocketSubscribeConfirmation.class);
    }

    if (obj.containsKey("unsubbed")) {
      return obj.mapTo(MarketDataWebSocketUnsubscribeConfirmation.class);
    }

    throw new UnsupportedOperationException(obj.toString());
  }

  private MarketDataWebSocketChannelMessage toMarketDataMessage(JsonObject obj) {
    final String ch = obj.getString("ch");
    synchronized (channelMessageTypes) {
      Class<? extends MarketDataWebSocketChannelMessage> type = channelMessageTypes.get(ch);
      return obj.mapTo(type);
    }
  }
}
