package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.common._OrderBookLevel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class DiffOrderBookChannel
    extends WebSocketChannel<DiffOrderBookChannel.Id, DiffOrderBookChannel.Message> {

  public static final String PREFIX = "diff_order_book_";

  DiffOrderBookChannel(DiffOrderBookChannel.Id id) {
    super(id);
  }

  @Override
  protected Class<DiffOrderBookChannel.Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<DiffOrderBookChannel.Message> {

    private Id(String currencyPair) {
      super(PREFIX + currencyPair);
    }

    public static Id of(String currencyPair) {
      return new Id(currencyPair);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Data> {}

  @NotThreadSafe
  public static final class Data {

    public Long timestamp;
    public Long microtimestamp;
    public List<_OrderBookLevel> bids;
    public List<_OrderBookLevel> asks;
  }
}
