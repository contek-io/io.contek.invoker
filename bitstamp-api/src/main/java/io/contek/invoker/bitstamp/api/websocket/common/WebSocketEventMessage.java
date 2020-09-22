package io.contek.invoker.bitstamp.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketEventMessage<Data> extends WebSocketMessage {

  public String event;
  public Data data;
}
