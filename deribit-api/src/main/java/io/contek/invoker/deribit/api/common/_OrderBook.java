package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

// Note that we used the same naming convention as in the API documentation, since Deribit uses
// snake case so we use it
// here as well.
@NotThreadSafe
public class _OrderBook {
  public Double ask_iv; // use -1 to represent empty maybe?
  public List<_OrderBookLevel> asks;
  public Double best_ask_amount;
  public Double best_ask_price;
  public Double best_bid_amount;
  public Double best_bid_price;
  public Double bid_iv;
  public List<_OrderBookLevel> bids;
  public Double current_funding;
  public Double delivery_price;
  public Double funding_8h;
  public _Greek greeks;
  public Double index_price;
  public String instrument_name;
  public Double interest_rate;
  public Double last_price;
  public Double mark_iv;
  public Double mark_price;
  public Double max_price;
  public Double min_price;
  public Double open_interest;
  public Double settlement_price;
  public String state;
  public _Stats stats;
  public Long timestamp;
  public Double underlying_index;
  public Double underlying_price;
}
