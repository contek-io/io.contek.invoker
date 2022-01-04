package io.contek.invoker.kraken.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketOutboundMessage extends AnyWebSocketMessage {

  public String event;
}
