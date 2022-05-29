package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

public final class OrderChannel extends WebSocketChannel<OrderChannel.Id, OrderChannel.Message> {

  OrderChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return OrderChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<OrderChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("order");
    }

    @Override
    public boolean accepts(OrderChannel.Message message) {
      return true;
    }
  }

  public static final class Data extends _Order {}

  public static final class Message extends WebSocketTableDataMessage<Data> {}
}
