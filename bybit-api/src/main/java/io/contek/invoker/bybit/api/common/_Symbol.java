package io.contek.invoker.bybit.api.common;

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

  @Override
  public String toString() {
    return "_Symbol{" +
            "name='" + name + '\'' +
            ", base_currency='" + base_currency + '\'' +
            ", quote_currency='" + quote_currency + '\'' +
            ", price_scale=" + price_scale +
            ", taker_fee=" + taker_fee +
            ", maker_fee=" + maker_fee +
            ", leverage_filter=" + leverage_filter +
            ", price_filter=" + price_filter +
            ", lot_size_filter=" + lot_size_filter +
            '}';
  }
}
