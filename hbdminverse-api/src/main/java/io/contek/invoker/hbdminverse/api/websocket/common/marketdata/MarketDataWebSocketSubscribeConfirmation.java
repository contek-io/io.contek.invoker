package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketConfirmation;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketSubscribeConfirmation extends WebSocketConfirmation {

  public String subbed;
}
