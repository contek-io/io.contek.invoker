package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketSubscriptionKeys {

  public static final String subscribe = "subscribe";
  public static final String unsubscribe = "unsubscribe";

  public static final String subscribed = "subscribed";
  public static final String unsubscribed = "unsubscribed";

  private WebSocketSubscriptionKeys() {}
}
