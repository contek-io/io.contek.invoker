package io.contek.invoker.bybit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketOperationKeys {

  public static final String auth = "auth";

  public static final String subscribe = "subscribe";

  public static final String unsubscribe = "unsubscribe";

  private WebSocketOperationKeys() {}
}
