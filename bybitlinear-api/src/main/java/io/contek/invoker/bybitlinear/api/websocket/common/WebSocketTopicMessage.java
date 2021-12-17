package io.contek.invoker.bybitlinear.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketTopicMessage extends AnyWebSocketMessage {

  public String topic;
}
