package io.contek.invoker.hbdmlinear.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketInboundMessage extends AnyWebSocketMessage {

  public Long ts;
}
