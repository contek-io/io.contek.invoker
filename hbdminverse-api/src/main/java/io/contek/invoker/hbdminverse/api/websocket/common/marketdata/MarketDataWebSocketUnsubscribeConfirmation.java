package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeConfirmation extends MarketDataWebSocketConfirmation {

  public String unsubbed;
}
