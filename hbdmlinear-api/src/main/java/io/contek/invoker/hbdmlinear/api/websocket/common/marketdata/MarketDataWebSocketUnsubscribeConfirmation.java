package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeConfirmation extends MarketDataWebSocketConfirmation {

  public String unsubbed;
}
