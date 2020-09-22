package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._OrderBookLevel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.market.OrderBookL2Channel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.text.MessageFormat.format;

@ThreadSafe
public final class OrderBookL2Channel extends WebSocketChannel<Message> {

  private final String instrument;

  OrderBookL2Channel(String instrument) {
    this.instrument = instrument;
  }

  @Override
  protected String getTopic() {
    return format("orderBookL2:{0}", instrument);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return message.data.stream().map(level -> level.symbol).anyMatch(instrument::equals);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_OrderBookLevel> {}
}
