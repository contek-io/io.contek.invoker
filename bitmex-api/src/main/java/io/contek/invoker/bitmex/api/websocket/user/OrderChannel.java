package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class OrderChannel extends WebSocketChannel<OrderChannel.Message> {

  public static final String ORDER = "order";

  @Override
  protected String getTopic() {
    return ORDER;
  }

  @Override
  protected Class<Message> getMessageType() {
    return OrderChannel.Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return message.table.equals(ORDER);
  }

  @NotThreadSafe
  public static final class Data extends _Order {}

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<Data> {}
}
