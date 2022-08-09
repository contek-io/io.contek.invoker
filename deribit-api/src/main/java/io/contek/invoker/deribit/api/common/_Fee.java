package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Fee {

  public String currency;
  public String fee_type;
  public String instrument_type;
  public Double maker_fee;
  public Double taker_fee;
}
