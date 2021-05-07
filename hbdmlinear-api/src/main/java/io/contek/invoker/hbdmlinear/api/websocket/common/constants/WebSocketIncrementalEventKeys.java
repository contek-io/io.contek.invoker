package io.contek.invoker.hbdmlinear.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketIncrementalEventKeys {

  public static final String _init = "init";

  public static final String _snapshot = "snapshot";

  public static final String _update = "update";

  private WebSocketIncrementalEventKeys() {}
}
