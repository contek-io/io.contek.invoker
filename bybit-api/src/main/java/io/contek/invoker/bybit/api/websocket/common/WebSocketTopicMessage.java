package io.contek.invoker.bybit.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public abstract class WebSocketTopicMessage extends AnyWebSocketMessage {

  public String topic;
}
