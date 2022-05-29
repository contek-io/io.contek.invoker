package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._orders;

public final class OrdersChannel
    extends WebSocketUserChannel<OrdersChannel.Id, OrdersChannel.Message> {

  OrdersChannel(OrdersChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return OrdersChannel.Message.class;
  }

  public static final class Id extends WebSocketUserChannelId<OrdersChannel.Message> {

    private Id(String instId) {
      super(_orders, instId);
    }

    public static OrdersChannel.Id of(String instId) {
      return new OrdersChannel.Id(instId);
    }
  }

  public static final class Data extends _Order {}

  public static final class Message extends WebSocketChannelPushData<Data> {}
}
