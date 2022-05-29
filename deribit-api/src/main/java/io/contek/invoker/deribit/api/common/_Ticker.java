package io.contek.invoker.deribit.api.common;

public class _Ticker {

  public double best_ask_amount;
  public double best_ask_price;
  public double best_bid_amount;
  public double best_bid_price;
  public double current_funding;
  public double funding_8h;
  public _Greek greeks;
  public double index_price;
  public String instrument_name;
  public double interest_rate;
  public double last_price;
  public double mark_price;
  public double max_price;
  public double min_price;
  public double open_interest;
  public double settlement_price;
  public String state;
  public _Stats stats;
  public long timestamp;

  @Override
  public String toString() {
    return "_Ticker{" +
            "best_ask_amount=" + best_ask_amount +
            ", best_ask_price=" + best_ask_price +
            ", best_bid_amount=" + best_bid_amount +
            ", best_bid_price=" + best_bid_price +
            ", current_funding=" + current_funding +
            ", funding_8h=" + funding_8h +
            ", greeks=" + greeks +
            ", index_price=" + index_price +
            ", instrument_name='" + instrument_name + '\'' +
            ", interest_rate=" + interest_rate +
            ", last_price=" + last_price +
            ", mark_price=" + mark_price +
            ", max_price=" + max_price +
            ", min_price=" + min_price +
            ", open_interest=" + open_interest +
            ", settlement_price=" + settlement_price +
            ", state='" + state + '\'' +
            ", stats=" + stats +
            ", timestamp=" + timestamp +
            '}';
  }
}
