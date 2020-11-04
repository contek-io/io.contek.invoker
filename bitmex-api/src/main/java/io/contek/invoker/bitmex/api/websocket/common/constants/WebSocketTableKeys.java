package io.contek.invoker.bitmex.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketTableKeys {
  // Site announcements
  public static final String _announcement = "announcement";

  // Trollbox chat
  public static final String _chat = "chat";

  // Statistics of connected users/bots
  public static final String _connected = "connected";

  // Updates of swap funding rates. Sent every funding interval (usually 8hrs)
  public static final String _funding = "funding";

  // Instrument updates including turnover and bid/ask
  public static final String _instrument = "instrument";

  // Daily Insurance Fund updates
  public static final String _insurance = "insurance";

  // Liquidation orders as they're entered into the book
  public static final String _liquidation = "liquidation";

  // Top 25 levels of level 2 order book
  public static final String _orderBookL2_25 = "orderBookL2_25";

  // Full level 2 order book
  public static final String _orderBookL2 = "orderBookL2";

  // Top 10 levels using traditional full book push
  public static final String _orderBook10 = "orderBook10";

  // System-wide notifications (used for short-lived messages)
  public static final String _publicNotifications = "publicNotifications";

  // Top level of the book
  public static final String _quote = "quote";

  // 1-minute quote bins
  public static final String _quoteBin1m = "quoteBin1m";

  // 5-minute quote bins
  public static final String _quoteBin5m = "quoteBin5m";

  // 1-hour quote bins
  public static final String _quoteBin1h = "quoteBin1h";

  // 1-day quote bins
  public static final String _quoteBin1d = "quoteBin1d";

  // Settlements
  public static final String _settlement = "settlement";

  // Live trades
  public static final String _trade = "trade";

  // 1-minute trade bins
  public static final String _tradeBin1m = "tradeBin1m";

  // 5-minute trade bins
  public static final String _tradeBin5m = "tradeBin5m";

  // 1-hour trade bins
  public static final String _tradeBin1h = "tradeBin1h";

  // 1-day trade bins
  public static final String _tradeBin1d = "tradeBin1d";

  // Affiliate statussuch as total referred users & payout %
  public static final String _affiliate = "affiliate";

  // Individual executions; can be multiple per order
  public static final String _execution = "execution";

  // Live updates on your orders
  public static final String _order = "order";

  // Updates on your current account balance and margin requirements
  public static final String _margin = "margin";

  // Updates on your positions
  public static final String _position = "position";

  // Individual notifications - currently not used
  public static final String _privateNotifications = "privateNotifications";

  // Deposit/Withdrawal updates
  public static final String _transact = "transact";

  // Bitcoin address balance dataincluding total deposits & withdrawals
  public static final String _wallet = "wallet";

  private WebSocketTableKeys() {}
}
