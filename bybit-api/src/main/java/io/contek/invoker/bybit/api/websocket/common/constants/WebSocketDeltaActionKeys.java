package io.contek.invoker.bybit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketDeltaActionKeys {

  public static final String delete = "delete";

  public static final String update = "update";

  public static final String insert = "insert";

  private WebSocketDeltaActionKeys() {}
}
