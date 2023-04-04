package io.contek.invoker.binancelinear.api.websocket.market.combined;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class WebSocketCommandConfirmation extends AnyWebSocketMessage {

  public Integer id;
}
