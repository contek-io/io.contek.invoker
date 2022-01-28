package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._OrderBook;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._books;

@ThreadSafe
public final class OrderBookChannel
    extends WebSocketMarketChannel<OrderBookChannel.Id, OrderBookChannel.Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<OrderBookChannel.Message> {

    private Id(String instId) {
      super(_books, instId);
    }

    public static OrderBookChannel.Id of(String instId) {
      return new OrderBookChannel.Id(instId);
    }
  }

  @NotThreadSafe
  public static final class Data extends _OrderBook {

    public String action;

    public Long checksum;

    public Double time;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<Data> {}
}
