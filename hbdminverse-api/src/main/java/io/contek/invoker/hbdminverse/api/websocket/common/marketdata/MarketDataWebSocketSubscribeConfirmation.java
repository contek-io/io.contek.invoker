package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketSubscribeConfirmation extends MarketDataWebSocketConfirmation {

  public String subbed;
}
