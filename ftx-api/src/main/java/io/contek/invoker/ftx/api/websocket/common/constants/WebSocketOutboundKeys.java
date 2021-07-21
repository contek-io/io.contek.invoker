package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketOutboundKeys {

  public static final String _subscribe = "subscribe";

  public static final String _unsubscribe = "unsubscribe";

  public static final String _login = "login";

  private WebSocketOutboundKeys() {}
}
