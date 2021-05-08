package io.contek.invoker.kraken.api.websocket.market;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final Topic topic;

  TradesChannel(Topic topic, WebSocketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.topic = topic;
  }

  @Override
  protected BaseWebSocketChannelId getId() {
    return String.format("%s_%s", WebSocketChannelKeys._trade, topic.getPair());
  }

  @Override
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return message.pair.equals(topic.getPair());
  }

  @Override
  protected Subscription getSubscription() {
    Subscription subscription = new Subscription();
    subscription.name = WebSocketChannelKeys._trade;
    return subscription;
  }

  @Override
  protected String getPair() {
    return topic.getPair();
  }

  @Immutable
  public static final class Topic {

    private final String pair;

    private String value;

    private Topic(String pair) {
      this.pair = pair;
    }

    public static Topic of(String pair) {
      return new Topic(pair);
    }

    public String getPair() {
      return pair;
    }

    @Override
    public boolean equals(@Nullable Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Topic topic = (Topic) o;
      return Objects.equals(pair, topic.pair);
    }

    @Override
    public int hashCode() {
      return Objects.hash(pair);
    }

    @Override
    public String toString() {
      if (value == null) {
        value = WebSocketChannelKeys._trade + "." + pair;
      }
      return value;
    }
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
