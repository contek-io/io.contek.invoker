package io.contek.invoker.okx.api.websocket.common;

import java.util.List;

public abstract class WebSocketChannelPushData<T> extends WebSocketInboundMessage {

  public WebSocketChannelArg arg;
  public List<T> data;
}
