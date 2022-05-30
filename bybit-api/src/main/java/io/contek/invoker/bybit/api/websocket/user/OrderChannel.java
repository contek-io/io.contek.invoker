package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import java.util.List;

public final class OrderChannel extends WebSocketChannel<OrderChannel.Id, OrderChannel.Message> {

  OrderChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<OrderChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("order");
    }
  }

  public static final class Message extends WebSocketTopicMessage {

    public List<_Order> data;

    @Override
    public String toString() {
      return "Message{" +
              "data=" + data +
              '}';
    }
  }
}
