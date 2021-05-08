package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._OrderBook;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Objects;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

@ThreadSafe
public final class OrderBookChannel extends MarketWebSocketChannel<OrderBookChannel.Message> {

  private final Topic topic;

  OrderBookChannel(Topic topic) {
    this.topic = topic;
  }

  @Override
  protected String getChannel() {
    return _orderbook;
  }

  @Override
  protected String getMarket() {
    return topic.getMarket();
  }

  @Override
  protected String getDisplayName() {
    return topic.toString();
  }

  @Override
  protected Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Override
  protected boolean accepts(OrderBookChannel.Message message) {
    return topic.getMarket().equals(message.market);
  }

  @Immutable
  public static final class Topic {

    private final String market;

    private String value;

    private Topic(String market) {
      this.market = market;
    }

    public static Topic of(String market) {
      return new Topic(market);
    }

    public String getMarket() {
      return market;
    }

    @Override
    public boolean equals(@Nullable Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Topic topic = (Topic) o;
      return Objects.equals(market, topic.market);
    }

    @Override
    public int hashCode() {
      return Objects.hash(market);
    }

    @Override
    public String toString() {
      if (value == null) {
        value = _orderbook + "." + market;
      }
      return value;
    }
  }

  @NotThreadSafe
  public static final class Data extends _OrderBook {

    public String action;

    public Long checksum;

    public Double time;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<OrderBookChannel.Data> {}
}
