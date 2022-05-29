package io.contek.invoker.kraken.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public abstract class WebSocketOutboundMessage extends AnyWebSocketMessage {

  public String event;
}
