package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Fee {

  public String currency;
  public String fee_type;
  public String instrument_type;
  public double maker_fee;
  public double taker_fee;

  @Override
  public String toString() {
    return "_Fee{"
        + "currency='"
        + currency
        + '\''
        + ", fee_type='"
        + fee_type
        + '\''
        + ", instrument_type='"
        + instrument_type
        + '\''
        + ", maker_fee="
        + maker_fee
        + ", taker_fee="
        + taker_fee
        + '}';
  }
}
