package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class WebSocketResponse extends WebSocketInboundMessage {

  public String event;
  public Subscription subscription;
  public String errorMessage;
  // This is for subscription message, we put it together as a WebSocketResponse.
  public String status;
}
