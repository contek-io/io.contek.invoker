package io.contek.invoker.bitmex.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketRequestOperationKeys {

  public static final String _authKeyExpires = "authKeyExpires";

  public static final String _subscribe = "subscribe";

  public static final String _unsubscribe = "unsubscribe";

  private WebSocketRequestOperationKeys() {}
}
