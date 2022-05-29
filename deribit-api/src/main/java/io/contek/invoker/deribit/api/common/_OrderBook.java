package io.contek.invoker.deribit.api.common;

import java.util.List;

// Note that we used the same naming convention as in the API documentation, since Deribit uses
// snake case so we use it
// here as well.

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

  @Override
  public String toString() {
    return "_OrderBook{" +
            "ask_iv=" + ask_iv +
            ", asks=" + asks +
            ", best_ask_amount=" + best_ask_amount +
            ", best_ask_price=" + best_ask_price +
            ", best_bid_amount=" + best_bid_amount +
            ", best_bid_price=" + best_bid_price +
            ", bid_iv=" + bid_iv +
            ", bids=" + bids +
            ", current_funding=" + current_funding +
            ", delivery_price=" + delivery_price +
            ", funding_8h=" + funding_8h +
            ", greeks=" + greeks +
            ", index_price=" + index_price +
            ", instrument_name='" + instrument_name + '\'' +
            ", interest_rate=" + interest_rate +
            ", last_price=" + last_price +
            ", mark_iv=" + mark_iv +
            ", mark_price=" + mark_price +
            ", max_price=" + max_price +
            ", min_price=" + min_price +
            ", open_interest=" + open_interest +
            ", settlement_price=" + settlement_price +
            ", state='" + state + '\'' +
            ", stats=" + stats +
            ", timestamp=" + timestamp +
            ", underlying_index=" + underlying_index +
            ", underlying_price=" + underlying_price +
            '}';
  }
}
