package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Ticker {

  public String symbol;
  public String bid_price; // Empty if none
  public String ask_price; // Empty if none
  public String last_price; // Empty if none
  public String last_tick_direction;
  public String prev_price_24h; // Empty if none
  public String price_24h_pcnt; // Empty if none
  public String high_price_24h; // Empty if none
  public String low_price_24h; // Empty if none
  public String prev_price_1h; // Empty if none
  public String price_1h_pcnt; // Empty if none
  public String mark_price; // Empty if none
  public String index_price; // Empty if none
  public double open_interest;
  public double open_value;
  public double total_turnover;
  public double turnover_24h;
  public double total_volume;
  public double volume_24h;
  public double funding_rate;
  public double predicted_funding_rate;
  public String next_funding_time;
  public int countdown_hour;
}
