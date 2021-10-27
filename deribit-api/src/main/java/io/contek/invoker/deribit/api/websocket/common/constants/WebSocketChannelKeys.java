package io.contek.invoker.deribit.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _channel = "channel";

  public static final String _trades = "trades";

  public static final String _book = "book";

  public static final String _user_orders = "user.orders";

  public static final String _user_portfolio = "user.portfolio";

  public static final String _user_trades = "user.orders";

  private WebSocketChannelKeys() {}
}
