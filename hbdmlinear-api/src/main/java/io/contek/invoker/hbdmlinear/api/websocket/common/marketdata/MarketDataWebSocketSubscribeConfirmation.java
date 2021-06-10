package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketConfirmation;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketSubscribeConfirmation extends WebSocketConfirmation {

  public String subbed;
}
