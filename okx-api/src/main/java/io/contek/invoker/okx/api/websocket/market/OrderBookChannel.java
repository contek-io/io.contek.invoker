package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._OrderBook;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys.*;

@ThreadSafe
public final class OrderBookChannel
    extends WebSocketMarketChannel<OrderBookChannel.Message, OrderBookChannel.Data> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<OrderBookChannel.Message> {

    private Id(String channel, String instId) {
      super(channel, instId);
    }

    public static OrderBookChannel.Id ofBooks(String instId) {
      return new OrderBookChannel.Id(_books, instId);
    }

    public static OrderBookChannel.Id ofBooks5(String instId) {
      return new OrderBookChannel.Id(_books5, instId);
    }

    public static OrderBookChannel.Id ofBooks50L2Tbt(String instId) {
      return new OrderBookChannel.Id(_books50_l2_tbt, instId);
    }

    public static OrderBookChannel.Id ofBooksL2Tbt(String instId) {
      return new OrderBookChannel.Id(_books_l2_tbt, instId);
    }
  }

  @NotThreadSafe
  public static final class Data extends _OrderBook {

    public Long checksum;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<Data> {

    public String action;
  }
}
