package io.contek.invoker.coinbasepro.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketMessageTypes {

  public static final String type = "type";

  public static final String subscribe = "subscribe";
  public static final String unsubscribe = "unsubscribe";
  public static final String subscriptions = "subscriptions";

  public static final String snapshot = "snapshot";
  public static final String l2update = "l2update";
  public static final String last_match = "last_match";
  public static final String match = "match";

  private WebSocketMessageTypes() {}
}
