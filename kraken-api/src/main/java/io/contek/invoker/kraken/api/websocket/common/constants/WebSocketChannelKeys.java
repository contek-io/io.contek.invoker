package io.contek.invoker.kraken.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _channel = "channel";

  public static final String _trade = "trade";

  public static final String _orderbook = "book";

  private WebSocketChannelKeys() {
  }
}
