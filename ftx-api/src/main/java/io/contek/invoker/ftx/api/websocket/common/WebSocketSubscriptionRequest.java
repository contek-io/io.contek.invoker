package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscriptionRequest extends WebSocketOutboundMessage {

  public String channel;
  public String market;
}
