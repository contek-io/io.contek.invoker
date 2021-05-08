package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.bybit.api.websocket.market.OrderBookChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public abstract class OrderBookChannel<Id extends WebSocketChannelId>
    extends WebSocketChannel<Id, Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @NotThreadSafe
  public abstract static class Message extends WebSocketTopicMessage {

    public String type;
    public Long cross_seq;
    public Long timestamp_e6;
  }

  @NotThreadSafe
  public static final class DeltaMessage extends Message {

    public DeltaData data;
  }

  @NotThreadSafe
  public static final class DeltaData {

    public List<OrderBookLevel> delete;
    public List<OrderBookLevel> update;
    public List<OrderBookLevel> insert;
    public Long transactTimeE6;
  }

  @NotThreadSafe
  public static final class SnapshotMessage extends Message {

    public List<OrderBookLevel> data;
  }

  @NotThreadSafe
  public static final class OrderBookLevel {

    public Double price; // Order price
    public String symbol; // Symbol
    public Long id; // Symbol
    public String side; // Side
    public Double size; // Position qty
  }
}
