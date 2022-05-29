package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import java.util.List;

public abstract class OrderBookChannel<Id extends WebSocketChannelId<OrderBookChannel.Message>>
    extends WebSocketChannel<Id, OrderBookChannel.Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public abstract static class Message extends WebSocketTopicMessage {

    public String type;
    public Long cross_seq;
    public Long timestamp_e6;
  }

  public static final class DeltaMessage extends Message {

    public DeltaData data;
  }

  public static final class DeltaData {

    public List<OrderBookLevel> delete;
    public List<OrderBookLevel> update;
    public List<OrderBookLevel> insert;
    public Long transactTimeE6;
  }

  public static final class SnapshotMessage extends Message {

    public List<OrderBookLevel> data;
  }

  public static final class OrderBookLevel {

    public Double price; // Order price
    public String symbol; // Symbol
    public Long id; // Symbol
    public String side; // Side
    public Double size; // Position qty
  }
}
