package io.contek.invoker.bitmex.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketRequestOperations {

  public static final String authKeyExpires = "authKeyExpires";

  public static final String subscribe = "subscribe";

  public static final String unsubscribe = "unsubscribe";

  private WebSocketRequestOperations() {}
}
