package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class MarketDataMarketWebSocketRequest extends AnyWebSocketMessage {

  public String id;
}
