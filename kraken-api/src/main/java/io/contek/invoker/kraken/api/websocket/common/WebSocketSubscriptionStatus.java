package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscriptionStatus extends WebSocketResponse {

  public String channelID;
  public String channelName;
  public String errorMessage;
  public String status;
  public String pair;
  public Subscription subscription;
}
