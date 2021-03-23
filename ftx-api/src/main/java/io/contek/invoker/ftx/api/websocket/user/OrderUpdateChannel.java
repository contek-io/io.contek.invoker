package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orders;

public class OrderUpdateChannel extends WebSocketChannel<OrderUpdateChannel.Message> {

  @Override
  protected String getDisplayName() {
    return "orderUpdateChannel";
  }

  @Override
  protected Class<Message> getMessageType() {
    return OrderUpdateChannel.Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    return true;
  }

  @Override
  protected String getChannel() {
    return _orders;
  }

  @Override
  protected String getMarket() {
    return "all";
  }

  @NotThreadSafe
  public static final class Data extends _Order {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<OrderUpdateChannel.Data> {}
}
