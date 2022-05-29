package io.contek.invoker.bitstamp.api.websocket.common;

public abstract class WebSocketEventMessage<Data> extends WebSocketMessage {

  public String event;
  public Data data;
}
