package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._OrderBookLevel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class OrderBookL2Channel extends WebSocketChannel<OrderBookL2Channel.Message> {

  OrderBookL2Channel(Id id) {
    super(id);
  }

  @Override
  public Class<OrderBookL2Channel.Message> getMessageType() {
    return OrderBookL2Channel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<OrderBookL2Channel.Message> {

    private final String instrument;

    private Id(String instrument) {
      super(format("orderBookL2:%s", instrument));
      this.instrument = instrument;
    }

    public static Id of(String instrument) {
      return new Id(instrument);
    }

    @Override
    public boolean accepts(Message message) {
      return message.data.stream().map(level -> level.symbol).anyMatch(instrument::equals);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_OrderBookLevel> {}
}
