package io.contek.invoker.bitmex.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketError {

  public int status;
  public String error;
}
