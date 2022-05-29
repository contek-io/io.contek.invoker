package io.contek.invoker.ftx.api.websocket.common;

public abstract class WebSocketChannelMessage<T> extends WebSocketInboundMessage {

  public String channel;
  public T data;
}
