package io.contek.invoker.bybitinverse.api.websocket.market;

import io.contek.invoker.bybitinverse.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitinverse.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitinverse.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

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
  public static final class Id extends WebSocketChannelId<TradeChannel.Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("trade.%s", symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public List<Trade> data;
  }

  @NotThreadSafe
  public static final class Trade {

    public String timestamp; // UTC time
    public Long trade_time_ms; // Millisecond timestamp
    public String symbol; // Symbol
    public String side; // Side
    public Double size; // Position qty
    public Double price; // Order price
    public String tick_direction; // Direction of price change
    public String trade_id; // Trade ID
    public Long cross_seq; // Cross sequence
  }
}
