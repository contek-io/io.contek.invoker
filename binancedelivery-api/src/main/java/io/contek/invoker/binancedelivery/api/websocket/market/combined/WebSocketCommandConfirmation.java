package io.contek.invoker.binancedelivery.api.websocket.market.combined;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketCommandConfirmation extends AnyWebSocketMessage {

  public Integer id;
}
