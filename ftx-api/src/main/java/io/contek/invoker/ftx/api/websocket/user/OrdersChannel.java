package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orders;

public final class OrdersChannel
    extends WebSocketUserChannel<OrdersChannel.Id, OrdersChannel.Message> {

  OrdersChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return OrdersChannel.Message.class;
  }

  public static final class Id extends WebSocketUserChannelId<OrdersChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_orders);
    }
  }

  public static final class Data extends _Order {}

  public static final class Message extends WebSocketChannelMessage<OrdersChannel.Data> {}
}
