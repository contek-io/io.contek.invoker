package io.contek.invoker.bitstamp.api.websocket.common;

public abstract class WebSocketChannelMessage<Data> extends WebSocketEventMessage<Data> {

  public String channel;
}
