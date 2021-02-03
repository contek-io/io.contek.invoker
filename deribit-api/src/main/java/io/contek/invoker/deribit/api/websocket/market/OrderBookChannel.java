package io.contek.invoker.deribit.api.websocket.market;

import com.google.common.base.Joiner;
import io.contek.invoker.deribit.api.common._OrderBook;
import io.contek.invoker.deribit.api.common._OrderBookLevel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

@ThreadSafe
public final class OrderBookChannel extends WebSocketChannel<OrderBookChannel.Message> {

  private final String instrumentName;

  OrderBookChannel(String instrumentName) {
    this.instrumentName = instrumentName;
  }

  @Override
  protected String getChannel() {
    // "book.BTC-24SEP21.none.1.100ms"
    return String.format("%s.%s.%s.%d.%s", _orderbook, instrumentName, "none", 20, "100ms");
  }

  @Override
  protected String getDisplayName() {
    return Joiner.on(':').join(_orderbook, instrumentName);
  }

  @Override
  protected Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Override
  protected boolean accepts(OrderBookChannel.Message message) {
    return instrumentName.equals(parseInstrumentName(message.params.channel));
  }

  private String parseInstrumentName(String name) {
    String[] parts = name.split("\\.");
    if (parts.length != 5) {
      return "";
    }
    return parts[1];
  }

  @NotThreadSafe
  public static final class _Data {

    public long timestamp;
    public String instrument_name;
    public long change_id;
    public List<_OrderBookLevel> bids;
    public List<_OrderBookLevel> asks;
  }

  public static final class Params {
    public String channel;
    public OrderBookChannel._Data data;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<OrderBookChannel.Params> {}
}


