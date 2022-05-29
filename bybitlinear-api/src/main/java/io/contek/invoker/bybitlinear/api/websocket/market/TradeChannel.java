package io.contek.invoker.bybitlinear.api.websocket.market;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import java.util.List;

public final class TradeChannel extends WebSocketChannel<TradeChannel.Id, TradeChannel.Message> {

  TradeChannel(TradeChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("trade.%s", symbol));
    }
  }

  public static final class Message extends WebSocketTopicMessage {

    public List<Trade> data;
  }

  public static final class Trade {

    public String timestamp; // UTC time
    public Long trade_time_ms; // Millisecond timestamp
    public String symbol; // Symbol
    public String side; // Side
    public Double size; // Position qty
    public Double price; // Order price
    public String tick_direction; // Direction of price change
    public String trade_id; // Trade ID
  }
}
