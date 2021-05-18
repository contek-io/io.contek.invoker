package io.contek.invoker.hbdmlinear.api.websocket.market;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class MarketWebSocketSubscribeRequest extends MarketWebSocketRequest {

  public String sub;
}
