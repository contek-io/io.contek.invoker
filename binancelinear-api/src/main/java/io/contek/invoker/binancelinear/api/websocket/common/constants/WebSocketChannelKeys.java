package io.contek.invoker.binancelinear.api.websocket.common.constants;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import static java.lang.String.format;

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

  public static String bookTicker(String symbol) {
    return channel(symbol, _bookTicker);
  }

  public static String trade(String symbol) {
    return channel(symbol, _trade);
  }

  public static String aggTrade(String symbol) {
    return channel(symbol, _aggTrade);
  }

  public static String depth(String symbol, String interval) {
    return channel(symbol, _depth, interval);
  }

  public static String depthPartial(String symbol, int levels, @Nullable String interval) {
    return switch (levels) {
      case 5 -> channel(symbol, _depth5, interval);
      case 10 -> channel(symbol, _depth10, interval);
      case 20 -> channel(symbol, _depth20, interval);
      default -> throw new IllegalArgumentException(format("Unsupported levels: %d", levels));
    };
  }

  public static String forceOrder(String symbol) {
    return channel(symbol, _forceOrder);
  }

  public static String channel(String symbol, String topic) {
    return channel(symbol, topic, null);
  }

  public static String channel(String symbol, String topic, @Nullable String interval) {
    if (interval != null) {
      return symbol + "@" + topic + "@" + interval;
    }
    return symbol + "@" + topic;
  }

  private WebSocketChannelKeys() {}
}
