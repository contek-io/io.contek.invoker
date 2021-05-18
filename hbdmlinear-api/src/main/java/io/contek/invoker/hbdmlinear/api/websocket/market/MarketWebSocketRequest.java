package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class MarketWebSocketRequest extends AnyWebSocketMessage {

  public String id;
}
