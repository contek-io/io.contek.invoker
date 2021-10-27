package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orders;

@ThreadSafe
public final class OrdersChannel
    extends WebSocketUserChannel<OrdersChannel.Id, OrdersChannel.Message> {

  OrdersChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return OrdersChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketUserChannelId<OrdersChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_orders);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Order {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<OrdersChannel.Data> {}
}
