package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.common._OrderBook;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys.books;

@ThreadSafe
public final class OrderBookChannel extends WebSocketMarketChannel<OrderBookChannel.Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketMarketChannelId<Message> {

    private Id(String channel, String instId) {
      super(channel, instId);
    }

    public static Id of(String instId, int depth, boolean l2, boolean tbt) {
      return new Id(books(depth, l2, tbt), instId);
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
