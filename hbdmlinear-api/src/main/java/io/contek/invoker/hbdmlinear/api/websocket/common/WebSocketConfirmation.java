package io.contek.invoker.hbdmlinear.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketConfirmation extends WebSocketInboundMessage {

  public String id;
  public String status;
}
