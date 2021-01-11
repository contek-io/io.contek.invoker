package io.contek.invoker.deribit.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.deribit.api.common._OrderBook;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderBookChannel extends WebSocketChannel<OrderBookChannel.Message> {

  private final String market;

  OrderBookChannel(String market) {
    this.market = market;
  }

  @Override
  protected String getChannel() {
    return WebSocketChannelKeys._orderbook;
  }

  @Override
  protected String getMarket() {
    return market;
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(WebSocketChannelKeys._orderbook, market);
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
