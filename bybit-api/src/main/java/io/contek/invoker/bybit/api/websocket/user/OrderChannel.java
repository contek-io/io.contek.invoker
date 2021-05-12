package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class OrderChannel extends WebSocketChannel<OrderChannel.Id, OrderChannel.Message> {

  OrderChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<OrderChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("order");
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public List<_Order> data;
  }
}
