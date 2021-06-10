package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketConfirmation;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeConfirmation extends WebSocketConfirmation {

  public String unsubbed;
}
