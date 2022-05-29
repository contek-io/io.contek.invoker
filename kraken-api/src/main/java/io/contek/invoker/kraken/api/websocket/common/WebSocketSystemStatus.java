package io.contek.invoker.kraken.api.websocket.common;

public final class WebSocketSystemStatus extends WebSocketGeneralMessage {

  public Long connectionID;
  public String status;
  public String version;
}
