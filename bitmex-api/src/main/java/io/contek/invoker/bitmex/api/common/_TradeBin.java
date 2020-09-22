package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _TradeBin {

  public String timestamp;
  public String symbol;
  public Double open;
  public Double high;
  public Double low;
  public Double close;
  public Long trades;
  public Double volume;
  public Double vwap;
  public Double lastSize;
  public Double turnover;
  public Double homeNotional;
  public Double foreignNotional;
}
