package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _InstrumentInfo {

  public String symbol;
  public String contractType;
  public String status;
  public String baseCoin;
  public String quoteCoin;
  public String launchTime;
  public String deliveryTime;
  public String deliveryFeeRate;
  public String priceScale;
  public _LeverageFilter leverageFilter;
  public _PriceFilter priceFilter;
  public _LotSizeFilter lotSizeFilter;
  public Boolean unifiedMarginTrade;
  public Integer fundingInterval;
  public String settleCoin;
}
