package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketInfoMessage extends WebSocketInboundMessage {

  public int code;
  public String msg;
}
