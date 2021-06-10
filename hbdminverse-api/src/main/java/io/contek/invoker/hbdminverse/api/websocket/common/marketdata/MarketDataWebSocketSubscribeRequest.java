package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketDataWebSocketSubscribeRequest extends MarketDataMarketWebSocketRequest {

  public String sub;
}
