package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSystemStatus extends WebSocketGeneralMessage {

  public Long connectionID;
  public String status;
  public String version;
}
