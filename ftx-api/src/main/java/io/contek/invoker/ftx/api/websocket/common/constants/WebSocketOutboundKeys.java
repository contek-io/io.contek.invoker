package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketOutboundKeys {

  public static final String subscribe = "subscribe";
  public static final String unsubscribe = "unsubscribe";

  private WebSocketOutboundKeys() {}
}
