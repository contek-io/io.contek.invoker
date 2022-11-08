package io.contek.invoker.bybitinverse.api.common;

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
  public String open_interest;
  public String open_value;
  public String total_turnover;
  public String turnover_24h;
  public String total_volume;
  public String volume_24h;
  public String funding_rate;
  public String predicted_funding_rate;
  public String next_funding_time;
  public String countdown_hour;
}
