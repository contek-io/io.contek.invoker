package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.WebSocketChannel;
import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketStreamMessage;
import io.contek.invoker.binancefutures.api.websocket.market.ForceOrderChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.text.MessageFormat.format;

@ThreadSafe
public final class ForceOrderChannel extends WebSocketChannel<Message> {

  private final String symbol;

  ForceOrderChannel(String symbol, WebSocketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.symbol = symbol;
  }

  @Override
  protected String getTopic() {
    return format("{0}@forceOrder", symbol.toLowerCase());
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    ForceOrderEvent data = message.data;
    return symbol.equals(data.o.s);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<ForceOrderEvent> {}
}
