package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class OrderbookChannel extends WebSocketChannel<OrderbookChannel.Message> {

  OrderbookChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(int depth, String symbol) {
      return new Id(String.format("orderbook.%d.%s", depth, symbol));
    }
  }

  @NotThreadSafe
  public abstract static class Message extends WebSocketTopicMessage<Orderbook> {}

  @NotThreadSafe
  public static final class Orderbook {

    public String s;
    public Side b;
    public Side a;
    public Long u;
    public Long seq;
  }

  @NotThreadSafe
  public static final class Side extends ArrayList<Level> {}

  @NotThreadSafe
  public static final class Level extends ArrayList<String> {}
}
