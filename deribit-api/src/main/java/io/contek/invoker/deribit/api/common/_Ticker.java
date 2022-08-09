package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Ticker {

  public Double best_ask_amount;
  public Double best_ask_price;
  public Double best_bid_amount;
  public Double best_bid_price;
  public Double current_funding;
  public Double funding_8h;
  public _Greek greeks;
  public Double index_price;
  public String instrument_name;
  public Double interest_rate;
  public Double last_price;
  public Double mark_price;
  public Double max_price;
  public Double min_price;
  public Double open_interest;
  public Double settlement_price;
  public String state;
  public _Stats stats;
  public Long timestamp;
}
