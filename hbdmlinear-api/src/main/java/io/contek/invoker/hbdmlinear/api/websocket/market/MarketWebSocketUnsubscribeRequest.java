package io.contek.invoker.hbdmlinear.api.websocket.market;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
final class MarketWebSocketUnsubscribeRequest extends MarketWebSocketRequest {

  public String unsub;
}
