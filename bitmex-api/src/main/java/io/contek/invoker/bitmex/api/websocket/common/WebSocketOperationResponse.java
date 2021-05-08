package io.contek.invoker.bitmex.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketOperationResponse extends AnyWebSocketMessage {

  public Boolean success;
  public String ret_msg;
  public String conn_id;
  public WebSocketOperationRequest request;
}
