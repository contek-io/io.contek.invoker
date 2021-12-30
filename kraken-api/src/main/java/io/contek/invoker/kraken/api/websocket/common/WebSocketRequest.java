package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketRequest extends WebSocketOutboundMessage {

  public Integer reqid;
}
