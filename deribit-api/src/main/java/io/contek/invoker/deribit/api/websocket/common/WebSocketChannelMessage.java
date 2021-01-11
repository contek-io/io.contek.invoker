package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketChannelMessage<T> extends WebSocketInboundMessage {

  public String channel;
  public String market;
  public T data;
}
