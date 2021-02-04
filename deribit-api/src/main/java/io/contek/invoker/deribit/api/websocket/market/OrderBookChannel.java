package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.OrderBook;
import io.contek.invoker.deribit.api.websocket.common.Params;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

@ThreadSafe
public final class OrderBookChannel extends WebSocketChannel<OrderBookChannel.Message> {

  private final String fullChannelName;

  OrderBookChannel(String instrumentName) {
    // "book.BTC-24SEP21.none.1.100ms"
    this.fullChannelName = String.format(
      "%s.%s.%s.%d.%s", _orderbook, instrumentName, "none", 20, "100ms");
  }

  @Override
  protected String getChannel() {
    return fullChannelName;
  }

  @Override
  protected String getDisplayName() {
    return fullChannelName;
  }

  @Override
  protected Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  @Override
  protected boolean accepts(OrderBookChannel.Message message) {
    return fullChannelName.equals(message.params.channel);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Params<OrderBook>> {
  }
}


