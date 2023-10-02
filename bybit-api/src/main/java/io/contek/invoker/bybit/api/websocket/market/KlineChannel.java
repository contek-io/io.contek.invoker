package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class KlineChannel extends WebSocketChannel<KlineChannel.Message> {

  KlineChannel(KlineChannel.Id id) {
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

    public static Id of(String interval, String symbol) {
      return new Id(String.format("kline.%s.%s", interval, symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends ArrayList<Kline> {}

  @NotThreadSafe
  public static final class Kline {

    public Long start;
    public Long end;
    public String interval;
    public String open;
    public String close;
    public String high;
    public String low;
    public String volume;
    public String turnover;
    public Boolean confirm;
    public Long timestamp;
  }
}
