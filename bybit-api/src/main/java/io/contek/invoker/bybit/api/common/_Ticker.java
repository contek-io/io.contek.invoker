package io.contek.invoker.bybit.api.common;

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

  @Override
  public String toString() {
    return "_Ticker{" +
            "symbol='" + symbol + '\'' +
            ", bid_price='" + bid_price + '\'' +
            ", ask_price='" + ask_price + '\'' +
            ", last_price='" + last_price + '\'' +
            ", last_tick_direction='" + last_tick_direction + '\'' +
            ", prev_price_24h='" + prev_price_24h + '\'' +
            ", price_24h_pcnt='" + price_24h_pcnt + '\'' +
            ", high_price_24h='" + high_price_24h + '\'' +
            ", low_price_24h='" + low_price_24h + '\'' +
            ", prev_price_1h='" + prev_price_1h + '\'' +
            ", price_1h_pcnt='" + price_1h_pcnt + '\'' +
            ", mark_price='" + mark_price + '\'' +
            ", index_price='" + index_price + '\'' +
            ", open_interest=" + open_interest +
            ", open_value=" + open_value +
            ", total_turnover=" + total_turnover +
            ", turnover_24h=" + turnover_24h +
            ", total_volume=" + total_volume +
            ", volume_24h=" + volume_24h +
            ", funding_rate=" + funding_rate +
            ", predicted_funding_rate=" + predicted_funding_rate +
            ", next_funding_time='" + next_funding_time + '\'' +
            ", countdown_hour=" + countdown_hour +
            '}';
  }
}
