package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.bybit.api.websocket.market.TradeChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class TradeChannel extends WebSocketChannel<Message> {

  public static final String TOPIC_PREFIX = "trade";

  private final String topic;

  public TradeChannel(String symbol) {
    topic = TOPIC_PREFIX + '.' + symbol;
  }

  @Override
  protected String getTopic() {
    return topic;
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return topic.equals(message.topic);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public ArrayList<Trade> data;
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
