package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _channel = "channel";

  public static final String _ticker = "ticker";

  public static final String _trades = "trades";

  public static final String _orderbook = "orderbook";

  public static final String _fills = "fills";

  public static final String _orders = "orders";

  private WebSocketChannelKeys() {}
}
