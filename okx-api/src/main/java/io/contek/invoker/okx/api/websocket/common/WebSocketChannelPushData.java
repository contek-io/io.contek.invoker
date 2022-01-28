package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class WebSocketChannelPushData<T> extends WebSocketInboundMessage {

  public WebSocketChannelArg arg;
  public List<T> data;
}
