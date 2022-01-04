package io.contek.invoker.deribit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _book = "book";

  public static final String _chart = "chart";

  public static final String _ticker = "ticker";

  public static final String _trades = "trades";

  public static final String _user_changes = "user.changes";

  public static final String _user_orders = "user.orders";

  private WebSocketChannelKeys() {}
}
