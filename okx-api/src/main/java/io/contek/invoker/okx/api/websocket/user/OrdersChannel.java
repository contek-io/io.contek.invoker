package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

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
      super(WebSocketChannelKeys._orders);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Order {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Data> {}
}
