package io.contek.invoker.kraken.api.websocket.common;

public abstract class WebSocketChannelDataMessage<T> extends WebSocketChannelMessage {

  public T data;
}
