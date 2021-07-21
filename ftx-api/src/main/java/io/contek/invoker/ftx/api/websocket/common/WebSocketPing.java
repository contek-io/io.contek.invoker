package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketPing extends WebSocketOutboundMessage {

  public WebSocketPing() {
    this.op = "ping";
  }
}
