package io.contek.invoker.coinbasepro.api.websocket.common;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketMessage extends AnyWebSocketMessage {

  public String type;
}
