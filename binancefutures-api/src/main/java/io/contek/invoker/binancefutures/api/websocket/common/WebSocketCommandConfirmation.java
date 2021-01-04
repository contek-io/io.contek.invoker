package io.contek.invoker.binancefutures.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketCommandConfirmation extends AnyWebSocketMessage {

  public Integer id;
}
