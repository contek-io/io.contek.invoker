package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class TradeChannel extends WebSocketChannel<TradeChannel.Message> {

  TradeChannel(TradeChannel.Id id) {
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

    public static Id of(String symbol) {
      return new Id(String.format("trade.%s", symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends ArrayList<Trade> {}

  @NotThreadSafe
  public static final class Trade {

    public Long t;
    public String s;
    public String S;
    public String v;
    public String p;
    public String L;
    public String i;
    public Boolean BT;
  }
}
