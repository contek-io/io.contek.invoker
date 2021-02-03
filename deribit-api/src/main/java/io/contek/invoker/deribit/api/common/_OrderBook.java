package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

// Note that we used the same naming convention as in the API documentation, since Deribit uses snake case so we use it
// here as well.
@NotThreadSafe
public class _OrderBook {
  public double ask_iv; // use -1 to represent empty maybe?
  public List<_OrderBookLevel> asks;
  public double best_ask_amount;
  public double best_ask_price;
  public double best_bid_amount;
  public double best_bid_price;
  public double bid_iv;
  public List<_OrderBookLevel> bids;
  public double current_funding;
  public double delivery_price;
  public double funding_8h;
  public _Greek greeks;
  public double index_price;
  public String instrument_name;
  public double interest_rate;
  public double last_price;
  public double mark_iv;
  public double mark_price;
  public double max_price;
  public double min_price;
  public double open_interest;
  public double settlement_price;
  public String state;
  public _Stats stats;
  public long timestamp;
  public double underlying_index;
  public double underlying_price;
}

