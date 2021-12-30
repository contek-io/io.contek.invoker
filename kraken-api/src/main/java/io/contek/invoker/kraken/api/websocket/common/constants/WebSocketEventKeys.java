package io.contek.invoker.kraken.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketEventKeys {

  public static final String _ping = "ping";

  public static final String _pong = "pong";

  public static final String _heartbeat = "heartbeat";

  public static final String _systemStatus = "systemStatus";

  public static final String _subscribe = "subscribe";

  public static final String _unsubscribe = "unsubscribe";

  public static final String _subscriptionStatus = "subscriptionStatus";

  private WebSocketEventKeys() {}
}
