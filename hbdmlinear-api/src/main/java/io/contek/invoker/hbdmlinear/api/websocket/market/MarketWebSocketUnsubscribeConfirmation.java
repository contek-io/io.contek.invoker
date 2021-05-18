package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketConfirmation;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketWebSocketUnsubscribeConfirmation extends WebSocketConfirmation {

  public String unsubbed;
}
