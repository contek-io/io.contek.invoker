package io.contek.invoker.hbdmlinear.api.websocket.market;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketTickMessage<T> extends WebSocketMarketDataMessage {

  public T tick;
}
