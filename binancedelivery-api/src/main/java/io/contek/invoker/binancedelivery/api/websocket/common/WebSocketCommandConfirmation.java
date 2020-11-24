package io.contek.invoker.binancedelivery.api.websocket.common;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketCommandConfirmation extends AnyWebSocketMessage {

  public Integer id;
}
