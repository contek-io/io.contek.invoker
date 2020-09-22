package io.contek.invoker.bitmex.api.websocket.common;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketRequestConfirmation extends AnyWebSocketMessage {

  public Boolean success;
  public WebSocketRequest request;
}
