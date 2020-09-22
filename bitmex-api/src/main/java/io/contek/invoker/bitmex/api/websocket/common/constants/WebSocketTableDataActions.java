package io.contek.invoker.bitmex.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketTableDataActions {

  public static final String partial = "partial";

  public static final String update = "update";

  public static final String insert = "insert";

  public static final String delete = "delete";

  private WebSocketTableDataActions() {}
}
