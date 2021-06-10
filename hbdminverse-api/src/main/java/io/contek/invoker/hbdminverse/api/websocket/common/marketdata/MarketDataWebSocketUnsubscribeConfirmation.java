package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketConfirmation;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeConfirmation extends WebSocketConfirmation {

  public String unsubbed;
}
