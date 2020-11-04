package io.contek.invoker.coinbasepro.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketMessageKeys {

  public static final String _type = "type";

  public static final String _subscribe = "subscribe";
  public static final String _unsubscribe = "unsubscribe";
  public static final String _subscriptions = "subscriptions";

  public static final String _snapshot = "snapshot";
  public static final String _l2update = "l2update";
  public static final String _last_match = "last_match";
  public static final String _match = "match";

  private WebSocketMessageKeys() {}
}
