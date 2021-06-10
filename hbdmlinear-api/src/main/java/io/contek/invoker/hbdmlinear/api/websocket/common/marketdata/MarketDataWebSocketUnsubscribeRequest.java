package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeRequest extends MarketDataMarketWebSocketRequest {

  public String unsub;
}
