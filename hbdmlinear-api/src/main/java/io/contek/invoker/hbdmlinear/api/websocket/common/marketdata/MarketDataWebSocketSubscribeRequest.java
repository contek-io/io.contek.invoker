package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketDataWebSocketSubscribeRequest extends MarketDataMarketWebSocketRequest {

  public String sub;
}
