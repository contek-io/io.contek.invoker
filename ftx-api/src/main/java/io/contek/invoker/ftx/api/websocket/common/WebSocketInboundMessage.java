package io.contek.invoker.ftx.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public abstract class WebSocketInboundMessage extends AnyWebSocketMessage {

  public String type;
}
