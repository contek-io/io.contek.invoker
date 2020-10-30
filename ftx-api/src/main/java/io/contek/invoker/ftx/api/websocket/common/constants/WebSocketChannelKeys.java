package io.contek.invoker.ftx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String channel = "channel";

  public static final String trades = "trades";

  public static final String orderbook = "orderbook";

  private WebSocketChannelKeys() {}
}
