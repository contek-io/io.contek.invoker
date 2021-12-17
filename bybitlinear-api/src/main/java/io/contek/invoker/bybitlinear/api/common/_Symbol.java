package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Symbol {

  public String name;
  public String base_currency;
  public String quote_currency;
  public int price_scale;
  public double taker_fee;
  public double maker_fee;
  public _LeverageFilter leverage_filter;
  public _PriceFilter price_filter;
  public _LotSizeFilter lot_size_filter;
}
