package io.contek.invoker.okx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketInboundKeys {

  public static final String _pong = "pong";

  public static final String _event = "event";

  public static final String _arg = "arg";

  public static final String _data = "data";

  public static final String _error = "error";

  public static final String _subscribe = "subscribe";

  public static final String _unsubscribe = "unsubscribe";
}
