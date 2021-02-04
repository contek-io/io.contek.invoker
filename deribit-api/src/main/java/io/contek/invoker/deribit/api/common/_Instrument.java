package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Instrument {

  public String base_currency;
  public double block_trade_commission;
  public long contract_size;
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
}
