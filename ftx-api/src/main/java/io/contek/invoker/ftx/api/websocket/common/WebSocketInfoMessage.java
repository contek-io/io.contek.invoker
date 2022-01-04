package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketInfoMessage extends WebSocketInboundMessage {

  public Integer code;
  public String msg;
}
