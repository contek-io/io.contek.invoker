package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketSubscribeConfirmation extends MarketDataWebSocketConfirmation {

  public String subbed;
}
