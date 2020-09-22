package io.contek.invoker.bitstamp.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketChannelMessage<Data> extends WebSocketEventMessage<Data> {

  public String channel;
}
