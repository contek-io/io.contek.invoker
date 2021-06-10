package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketDataWebSocketUnsubscribeRequest extends MarketDataMarketWebSocketRequest {

  public String unsub;
}
