package io.contek.invoker.kraken.api.websocket.market;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.common._OrderBook;
import io.contek.invoker.kraken.api.common._OrderBookLevel;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.contek.invoker.kraken.api.common._OrderBookLevel.toOrderBookLevel;

@ThreadSafe
public final class OrderBookChannel extends WebSocketChannel<OrderBookChannel.Message> {

  public static final String BID_SNAPSHOT_KEY = "bs";
  public static final String ASK_SNAPSHOT_KEY = "as";
  public static final String BID_INCREMENTAL_KEY = "b";
  public static final String ASK_INCREMENTAL_KEY = "a";
  public static final String CHECK_SUM_KEY = "c";
  private final String symbolName;

  OrderBookChannel(String symbolName) {
    this.symbolName = symbolName;
  }

  @Override
  protected Subscription getSubscription() {
    Subscription subscription = new Subscription();
    subscription.name = "book";
    subscription.depth = 1000;
    return subscription;
  }

  @Override
  protected List<String> getPair() {
    return Collections.singletonList(symbolName);
  }

  @Override
  protected String getDisplayName() {
    return String.format("order_book_%s", symbolName);
  }

  @Override
  protected Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Override
  protected boolean accepts(OrderBookChannel.Message message) {
    return symbolName.equals(message.pair);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<_OrderBook> {}

  public static Message toOrderBook(JsonArray jsonArray) {
    JsonElement jsonElement = jsonArray.get(1);

    Message res = new Message();
    res.channelID = jsonArray.get(0).getAsInt();
    res.channelName = jsonArray.get(jsonArray.size() - 2).getAsString();
    res.pair = jsonArray.get(jsonArray.size() - 1).getAsString();
    res.params = new _OrderBook();
    res.params.bs = Collections.emptyList();
    res.params.as = Collections.emptyList();
    res.params.b = Collections.emptyList();
    res.params.a = Collections.emptyList();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    if (jsonObject.has(BID_SNAPSHOT_KEY)) {
      res.params.bs = toOrderBookEntries(jsonObject.get(BID_SNAPSHOT_KEY));
    }

    if (jsonObject.has(ASK_SNAPSHOT_KEY)) {
      res.params.as = toOrderBookEntries(jsonObject.get(ASK_SNAPSHOT_KEY));
    }

    if (jsonObject.has(BID_INCREMENTAL_KEY)) {
      res.params.b = toOrderBookEntries(jsonObject.get(BID_INCREMENTAL_KEY));
    }

    if (jsonObject.has(ASK_INCREMENTAL_KEY)) {
      res.params.a = toOrderBookEntries(jsonObject.get(ASK_INCREMENTAL_KEY));
    }

    if (jsonArray.size() == 5) {
      jsonObject = jsonArray.get(2).getAsJsonObject();
      if (jsonObject.has(BID_INCREMENTAL_KEY)) {
        res.params.b = toOrderBookEntries(jsonObject.get(BID_INCREMENTAL_KEY));
      }
    }

    return res;
  }

  private static List<_OrderBookLevel> toOrderBookEntries(JsonElement jsonArray) {

    List<_OrderBookLevel> orderBookEntries = new ArrayList<>();
    for (JsonElement jsonElement : jsonArray.getAsJsonArray()) {

      orderBookEntries.add(toOrderBookLevel(jsonElement.getAsJsonArray()));

    }
    return orderBookEntries;
  }
}


