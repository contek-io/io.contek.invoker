package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._OrderBook;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.WebSocketChannelId;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

@ThreadSafe
public final class OrderBookChannel
    extends WebSocketChannel<OrderBookChannel.Id, OrderBookChannel.Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<OrderBookChannel.Message> {

    private Id(String market) {
      super(_orderbook, market);
    }

    public static OrderBookChannel.Id of(String market) {
      return new OrderBookChannel.Id(market);
    }
  }

  @NotThreadSafe
  public static final class Data extends _OrderBook {

    public String action;

    public Long checksum;

    public Double time;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketMarketMessage<OrderBookChannel.Data> {}
}
