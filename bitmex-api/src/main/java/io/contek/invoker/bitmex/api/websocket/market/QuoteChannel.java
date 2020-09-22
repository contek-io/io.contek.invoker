package io.contek.invoker.bitmex.api.websocket.market;

import io.contek.invoker.bitmex.api.common._Quote;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.market.QuoteChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.text.MessageFormat.format;

@ThreadSafe
public final class QuoteChannel extends WebSocketChannel<Message> {

  private final String instrument;

  QuoteChannel(String instrument) {
    this.instrument = instrument;
  }

  @Override
  protected String getTopic() {
    return format("quote:{0}", instrument);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return message.data.stream().map(quote -> quote.symbol).anyMatch(instrument::equals);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_Quote> {}
}
