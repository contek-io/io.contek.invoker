package io.contek.invoker.bybitlinear.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketDeltaActionKeys {

  public static final String _delete = "delete";

  public static final String _update = "update";

  public static final String _insert = "insert";

  private WebSocketDeltaActionKeys() {}
}
