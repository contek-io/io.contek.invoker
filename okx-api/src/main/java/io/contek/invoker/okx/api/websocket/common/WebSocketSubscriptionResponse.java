package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscriptionResponse extends WebSocketInboundMessage {

  public String channel;
  public String market;
}
