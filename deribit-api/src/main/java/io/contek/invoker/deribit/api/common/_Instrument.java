package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Instrument {

  public String base_currency;
  public double block_trade_commission;
  public double contract_size;
  public long creation_timestamp;
  public long expiration_timestamp;
  public String instrument_name;
  public boolean is_active;
  public String kind;
  public long leverage;
  public double maker_commission;
  public double min_trade_amount;
  public String option_type;
  public String quote_currency;
  public String settlement_period;
  public double strike;
  public double taker_commission;
  public double tick_size;

  @Override
  public String toString() {
    return "_Instrument{" +
            "base_currency='" + base_currency + '\'' +
            ", block_trade_commission=" + block_trade_commission +
            ", contract_size=" + contract_size +
            ", creation_timestamp=" + creation_timestamp +
            ", expiration_timestamp=" + expiration_timestamp +
            ", instrument_name='" + instrument_name + '\'' +
            ", is_active=" + is_active +
            ", kind='" + kind + '\'' +
            ", leverage=" + leverage +
            ", maker_commission=" + maker_commission +
            ", min_trade_amount=" + min_trade_amount +
            ", option_type='" + option_type + '\'' +
            ", quote_currency='" + quote_currency + '\'' +
            ", settlement_period='" + settlement_period + '\'' +
            ", strike=" + strike +
            ", taker_commission=" + taker_commission +
            ", tick_size=" + tick_size +
            '}';
  }
}
