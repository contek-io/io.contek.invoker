package io.contek.invoker.deribit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketInboundKeys {

  public static final String _type = "type";

  public static final String _error = "error";

  public static final String _subscribed = "subscription";

  public static final String _unsubscribed = "unsubscription";

  public static final String _info = "info";
}
