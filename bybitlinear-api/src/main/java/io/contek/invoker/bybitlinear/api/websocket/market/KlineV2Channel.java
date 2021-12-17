package io.contek.invoker.bybitlinear.api.websocket.market;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class KlineV2Channel
    extends WebSocketChannel<KlineV2Channel.Id, KlineV2Channel.Message> {

  KlineV2Channel(KlineV2Channel.Id id) {
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
      return new Id(String.format("klineV2.%s.%s", interval, symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public List<KlineV2> data;
    public Long timestamp_e6;
  }

  @NotThreadSafe
  public static final class KlineV2 {

    public Long start; // Start timestamp point for result, in seconds
    public Long end; // End timestamp point for result, in seconds
    public Double open; // Starting price
    public Double close; // Closing price
    public Double high; // Maximum price
    public Double low; // Minimum price
    public Double volume; // Trading volume
    public Double turnover; // Transaction amount
    public Boolean confirm; // Is confirm
    public Long cross_seq; // Cross sequence (internal value)
    public Long timestamp; // End timestamp point for result, in seconds
  }
}
