package io.contek.invoker.bybitlinear.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public final class WebSocketOperationResponse extends AnyWebSocketMessage {

  public Boolean success;
  public String ret_msg;
  public String conn_id;
  public WebSocketOperationRequest request;
}
