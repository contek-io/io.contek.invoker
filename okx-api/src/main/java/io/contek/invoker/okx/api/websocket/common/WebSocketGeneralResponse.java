package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketGeneralResponse extends WebSocketEvent {

  public String code;
  public String msg;
}
