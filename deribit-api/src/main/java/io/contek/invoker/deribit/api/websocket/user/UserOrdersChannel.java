package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.common._Order;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import static java.lang.String.format;

public final class UserOrdersChannel
    extends UserWebSocketChannel<UserOrdersChannel.Id, UserOrdersChannel.Message> {

  UserOrdersChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<UserOrdersChannel.Message> getMessageType() {
    return UserOrdersChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<UserOrdersChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("user.orders.%s.%s", instrumentName, interval));
    }
  }

  public static final class Message extends WebSocketSingleChannelMessage<Data> {}

  public static final class Data extends _Order {}
}
