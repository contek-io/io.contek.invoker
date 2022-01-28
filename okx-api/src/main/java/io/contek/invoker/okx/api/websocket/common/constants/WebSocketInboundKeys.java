package io.contek.invoker.okx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketInboundKeys {

  public static final String _type = "type";

  public static final String _error = "error";

  public static final String _subscribed = "subscribed";

  public static final String _unsubscribed = "unsubscribed";

  public static final String _partial = "partial";

  public static final String _update = "update";

  public static final String _info = "info";
}
