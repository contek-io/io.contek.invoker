package io.contek.invoker.deribit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketAuthGrantTypeKeys {

  public static final String _client_credentials = "client_credentials";

  public static final String _client_signature = "client_signature";

  public static final String _refresh_token = "refresh_token";

  private WebSocketAuthGrantTypeKeys() {}
}
