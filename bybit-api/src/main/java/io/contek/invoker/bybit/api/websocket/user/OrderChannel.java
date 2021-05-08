package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class OrderChannel extends WebSocketChannel<OrderChannel.Message> {

  public static final String ORDER = "order";

  @Override
  protected String getTopic() {
    return ORDER;
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return message.topic.equals(ORDER);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public List<_Order> data;
  }
}
