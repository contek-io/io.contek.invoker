package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._orders;

@ThreadSafe
public final class OrdersChannel extends WebSocketUserChannel<OrdersChannel.Message, _Order> {

  OrdersChannel(OrdersChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketUserChannelId<Message> {

    private Id(String instId) {
      super(_orders, instId);
    }

    public static Id of(String instId) {
      return new Id(instId);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<_Order> {}
}
