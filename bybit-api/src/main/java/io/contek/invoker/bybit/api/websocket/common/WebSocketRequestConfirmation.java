package io.contek.invoker.bybit.api.websocket.common;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketRequestConfirmation extends AnyWebSocketMessage {

  public Boolean success;
  public String ret_msg;
  public String conn_id;
  public WebSocketRequest request;
}
