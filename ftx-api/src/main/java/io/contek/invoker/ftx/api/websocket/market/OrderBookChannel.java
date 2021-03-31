package io.contek.invoker.ftx.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.ftx.api.common._OrderBook;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

@ThreadSafe
public final class OrderBookChannel extends MarketWebSocketChannel<OrderBookChannel.Message> {

  private final String market;

  OrderBookChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return _orderbook;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(_orderbook, market);
  }

  @Override
  protected Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Override
  protected boolean accepts(OrderBookChannel.Message message) {
    return market.equals(message.market);
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
