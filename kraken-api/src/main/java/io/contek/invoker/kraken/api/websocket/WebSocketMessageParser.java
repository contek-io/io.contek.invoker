package io.contek.invoker.kraken.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketComponent;
import io.contek.invoker.commons.websocket.WebSocketTextMessageParser;
import io.contek.invoker.kraken.api.common._Book;
import io.contek.invoker.kraken.api.common._BookLevel;
import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.common.*;
import io.contek.invoker.kraken.api.websocket.market.BookChannel;
import io.contek.invoker.kraken.api.websocket.market.TradeChannel;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static io.contek.invoker.kraken.api.common._BookLevel.toOrderBookLevel;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._book;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._trade;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketEventKeys.*;

final class WebSocketMessageParser extends WebSocketTextMessageParser {

  private static final String FIELD_EVENT = "event";

  private WebSocketMessageParser() {}

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static WebSocketChannelDataMessage<?> toDataMessage(JsonArray array) {
    // The type of the response is in the second to the last position.
    String type = array.getString(array.size() - 2);
    if (type.equals(_trade)) {
      return toTradeChannelMessage(array);
    } else if (type.startsWith(_book)) {
      return toBookChannelMessage(array);
    } else {
      throw new RuntimeException(String.format("Invalid type %s", type));
    }
  }

  public static BookChannel.Message toBookChannelMessage(JsonArray array) {
    BookChannel.Message result = new BookChannel.Message();
    result.channelID = array.getInteger(0);
    result.channelName = array.getString(array.size() - 2);
    result.pair = array.getString(array.size() - 1);

    result.data = new _Book();
    result.data.bs = ImmutableList.of();
    result.data.as = ImmutableList.of();
    result.data.b = ImmutableList.of();
    result.data.a = ImmutableList.of();

    for (int i = 1; i < array.size() - 2; i++) {
      JsonObject obj = array.getJsonObject(i);

      if (obj.containsKey("bs")) {
        result.data.bs = toOrderBookEntries(obj.getJsonArray("bs"));
      }

      if (obj.containsKey("as")) {
        result.data.as = toOrderBookEntries(obj.getJsonArray("as"));
      }

      if (obj.containsKey("b")) {
        result.data.b = toOrderBookEntries(obj.getJsonArray("b"));
      }

      if (obj.containsKey("a")) {
        result.data.a = toOrderBookEntries(obj.getJsonArray("a"));
      }
    }

    return result;
  }

  private static List<_BookLevel> toOrderBookEntries(JsonArray jsonArray) {
    List<_BookLevel> orderBookEntries = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      final JsonArray arr = jsonArray.getJsonArray(i);
      orderBookEntries.add(toOrderBookLevel(arr));
    }
    return orderBookEntries;
  }

  private static TradeChannel.Message toTradeChannelMessage(JsonArray array) {
    JsonArray trades_arr = array.getJsonArray(1);

    TradeChannel.Message trades = new TradeChannel.Message();
    trades.pair = array.getString(3);
    trades.data = new ArrayList<>();
    for (int i = 0; i < trades_arr.size(); i++) {
      final JsonArray trade_arr = trades_arr.getJsonArray(i);
      _Trade trade = new _Trade();
      trade.price = trade_arr.getDouble(0);
      trade.volume = trade_arr.getDouble(1);
      trade.time = trade_arr.getDouble(2);
      trade.side = trade_arr.getString(3);
      trade.orderType = trade_arr.getString(4);
      trade.misc = trade_arr.getString(5);
      trades.data.add(trade);
    }

    return trades;
  }

  @Override
  public void register(IWebSocketComponent component) {}

  @Override
  protected AnyWebSocketMessage fromText(String text) {
    try {
      final JsonObject obj = new JsonObject(text);
      return toWebSocketResponse(obj);
    } catch (Exception ignored) {
    }

    try {
      final JsonArray array = new JsonArray(text);
      return toDataMessage(array);
    } catch (Exception ignored) {
    }

    throw new IllegalArgumentException(text);
  }

  private WebSocketGeneralMessage toWebSocketResponse(JsonObject obj) {
    String event = obj.getString(FIELD_EVENT);
    return switch (event) {
      case _pong -> obj.mapTo(WebSocketPongResponse.class);
      case _heartbeat -> obj.mapTo(WebSocketHeartbeat.class);
      case _systemStatus -> obj.mapTo(WebSocketSystemStatus.class);
      case _subscriptionStatus -> obj.mapTo(WebSocketSubscriptionStatus.class);
      default -> throw new UnsupportedOperationException(event);
    };
  }

  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
