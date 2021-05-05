package io.contek.invoker.kraken.api.websocket.market;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final String symbolName;

  TradesChannel(String symbolName) {
    this.symbolName = symbolName;
  }

  @Override
  protected String getDisplayName() {
    return String.format("%s_%s", WebSocketChannelKeys._trade, symbolName);
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return message.pair.equals(symbolName);
  }

  @Override
  protected Subscription getSubscription() {
    Subscription subscription = new Subscription();
    subscription.name = WebSocketChannelKeys._trade;
    return subscription;
  }

  @Override
  protected List<String> getPair() {
    return Collections.singletonList(this.symbolName);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<List<_Trade>> {

    public static Message fromJsonArray(JsonArray arr) {
      JsonElement trades_arr = arr.get(1);

      Message trades = new Message();
      trades.pair = arr.get(3).getAsString();
      trades.params = new ArrayList<>();
      for (JsonElement element : trades_arr.getAsJsonArray()) {
        JsonArray trade_arr = element.getAsJsonArray();
        _Trade trade = new _Trade();
        trade.price = trade_arr.get(0).getAsDouble();
        trade.volume = trade_arr.get(1).getAsDouble();
        trade.time = trade_arr.get(2).getAsDouble();
        trade.side = trade_arr.get(3).getAsString();
        trade.orderType = trade_arr.get(4).getAsString();
        trade.misc = trade_arr.get(5).getAsString();
        trades.params.add(trade);
      }

      return trades;
    }
  }
}
