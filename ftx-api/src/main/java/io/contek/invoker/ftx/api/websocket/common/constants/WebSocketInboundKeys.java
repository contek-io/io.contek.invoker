package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketInboundKeys {

  public static final String type = "type";

  public static final String error = "error";
  public static final String subscribed = "subscribed";
  public static final String unsubscribed = "unsubscribed";

  public static final String partial = "partial";

  public static final String update = "update";
}
