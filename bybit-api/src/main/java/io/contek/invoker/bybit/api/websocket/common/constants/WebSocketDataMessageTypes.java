package io.contek.invoker.bybit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketDataMessageTypes {

  public static final String snapshot = "snapshot";

  public static final String delta = "delta";

  private WebSocketDataMessageTypes() {}
}
