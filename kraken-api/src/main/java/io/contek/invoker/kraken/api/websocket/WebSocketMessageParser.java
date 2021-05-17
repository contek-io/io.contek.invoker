package io.contek.invoker.kraken.api.websocket;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;
import io.contek.invoker.kraken.api.common._Book;
import io.contek.invoker.kraken.api.common._BookLevel;
import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;
import io.contek.invoker.kraken.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.kraken.api.websocket.common.WebSocketResponse;
import io.contek.invoker.kraken.api.websocket.market.BookChannel;
import io.contek.invoker.kraken.api.websocket.market.TradeChannel;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.List;

import static io.contek.invoker.kraken.api.common._BookLevel.toOrderBookLevel;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._book;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._trade;

@Immutable
final class WebSocketMessageParser implements IWebSocketMessageParser {

  private final Gson gson = new Gson();

  static WebSocketMessageParser getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public AnyWebSocketMessage parse(String text) {
    JsonElement json = gson.fromJson(text, JsonElement.class);

    if (json.isJsonArray()) {
      return toDataMessage(json.getAsJsonArray());
    } else if (json.isJsonObject()) {
      return toConfirmationMessage(json.getAsJsonObject());
    } else {
      throw new IllegalArgumentException(text);
    }
  }

  private WebSocketInboundMessage toConfirmationMessage(JsonObject obj) {
    return gson.fromJson(obj, WebSocketResponse.class);
  }

  private static WebSocketChannelDataMessage<?> toDataMessage(JsonArray array) {
    // The type of the response is in the second to the last position.
    String type = array.get(array.size() - 2).getAsString();
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
    result.channelID = array.get(0).getAsInt();
    result.channelName = array.get(array.size() - 2).getAsString();
    result.pair = array.get(array.size() - 1).getAsString();

    result.data = new _Book();
    result.data.bs = ImmutableList.of();
    result.data.as = ImmutableList.of();
    result.data.b = ImmutableList.of();
    result.data.a = ImmutableList.of();

    for (int i = 1; i < array.size() - 2; i++) {
      JsonObject obj = array.get(i).getAsJsonObject();

      if (obj.has("bs")) {
        result.data.bs = toOrderBookEntries(obj.get("bs"));
      }

      if (obj.has("as")) {
        result.data.as = toOrderBookEntries(obj.get("as"));
      }

      if (obj.has("b")) {
        result.data.b = toOrderBookEntries(obj.get("b"));
      }

      if (obj.has("a")) {
        result.data.a = toOrderBookEntries(obj.get("a"));
      }
    }

    return result;
  }

  private static List<_BookLevel> toOrderBookEntries(JsonElement jsonArray) {
    List<_BookLevel> orderBookEntries = new ArrayList<>();
    for (JsonElement jsonElement : jsonArray.getAsJsonArray()) {
      orderBookEntries.add(toOrderBookLevel(jsonElement.getAsJsonArray()));
    }
    return orderBookEntries;
  }

  private static TradeChannel.Message toTradeChannelMessage(JsonArray array) {
    JsonElement trades_arr = array.get(1);

    TradeChannel.Message trades = new TradeChannel.Message();
    trades.pair = array.get(3).getAsString();
    trades.data = new ArrayList<>();
    for (JsonElement element : trades_arr.getAsJsonArray()) {
      JsonArray trade_arr = element.getAsJsonArray();
      _Trade trade = new _Trade();
      trade.price = trade_arr.get(0).getAsDouble();
      trade.volume = trade_arr.get(1).getAsDouble();
      trade.time = trade_arr.get(2).getAsDouble();
      trade.side = trade_arr.get(3).getAsString();
      trade.orderType = trade_arr.get(4).getAsString();
      trade.misc = trade_arr.get(5).getAsString();
      trades.data.add(trade);
    }

    return trades;
  }

  private WebSocketMessageParser() {}

  @Immutable
  private static final class InstanceHolder {

    private static final WebSocketMessageParser INSTANCE = new WebSocketMessageParser();

    private InstanceHolder() {}
  }
}
