package io.contek.invoker.ftx.api.websocket.common;

public class WebSocketPing extends WebSocketOutboundMessage {

  public WebSocketPing() {
    super.op = "ping";
  }
}
