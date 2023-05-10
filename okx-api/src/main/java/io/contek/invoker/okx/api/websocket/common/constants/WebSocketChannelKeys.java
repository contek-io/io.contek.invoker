package io.contek.invoker.okx.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketChannelKeys {

  public static final String _channel = "channel";

  public static final String _tickers = "tickers";

  public static final String _trades = "trades";

  public static final String _mark_price = "mark-price";

  public static final String _index_tickers = "index-tickers";

  public static final String _books = "books";

  public static final String _books5 = "books5";

  public static final String _bbo_tbt = "bbo-tbt";

  public static final String _books50_l2_tbt = "books50-l2-tbt";

  public static final String _books_l2_tbt = "books-l2-tbt";

  public static final String _positions = "positions";

  public static final String _orders = "orders";

  public static String books(int depth, boolean l2, boolean tbt) {
    if (l2) {
      if (depth == 50) {
        return _books50_l2_tbt;
      }
      if (depth == 400) {
        if (tbt) {
          return _books_l2_tbt;
        }
        return _books;
      }

      throw new IllegalArgumentException("Unsupported L2 depth: " + depth);
    }

    if (depth == 1) {
      return _bbo_tbt;
    }
    if (depth == 5) {
      return _books5;
    }

    throw new IllegalArgumentException("Unsupported partial depth: " + depth);
  }

  private WebSocketChannelKeys() {}
}
