package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Ticker {

  public String symbol;
  public double bid_price;
  public double ask_price;
  public double last_price;
  public String last_tick_direction;
  public double prev_price_24h;
  public double price_24h_pcnt;
  public double high_price_24h;
  public double low_price_24h;
  public double prev_price_1h;
  public double price_1h_pcnt;
  public double mark_price;
  public double index_price;
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
