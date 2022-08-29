package io.contek.invoker.binancedelivery.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _bookTicker = "bookTicker";

  public static final String _trade = "trade";

  public static final String _aggTrade = "aggTrade";

  public static final String _depth = "depth";

  public static final String _depth5 = "depth5";

  public static final String _depth10 = "depth10";

  public static final String _depth20 = "depth20";

  public static final String _forceOrder = "forceOrder";

  private WebSocketChannelKeys() {}
}
