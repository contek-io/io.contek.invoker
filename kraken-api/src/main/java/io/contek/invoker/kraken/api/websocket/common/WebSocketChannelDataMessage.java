package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketChannelDataMessage<T> extends WebSocketChannelMessage {

  public T data;
}
