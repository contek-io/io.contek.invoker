package io.contek.invoker.coinbasepro.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketChannelMessage extends WebSocketMessage {

  public String product_id;
}
