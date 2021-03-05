package io.contek.invoker.kraken.api.common.constants;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class _Fee {
  public String currency;
  public String fee_type;
  public String instrument_type;
  public double maker_fee;
  public double taker_fee;
}
