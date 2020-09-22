package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.common._OrderBookLevel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.bitstamp.api.websocket.market.DiffOrderBookChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class DiffOrderBookChannel extends WebSocketChannel<Message> {

  public static final String PREFIX = "diff_order_book_";

  DiffOrderBookChannel(String currencyPair) {
    super(PREFIX + currencyPair);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
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
