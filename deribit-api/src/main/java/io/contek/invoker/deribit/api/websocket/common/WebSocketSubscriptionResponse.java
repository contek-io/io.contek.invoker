package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscriptionResponse extends WebSocketInboundMessage {

  public String channel;
  public String market;
}
