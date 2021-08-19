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

  @Override
  public String toString() {
    return "_TradeBin{" +
            "timestamp='" + timestamp + '\'' +
            ", symbol='" + symbol + '\'' +
            ", open=" + open +
            ", high=" + high +
            ", low=" + low +
            ", close=" + close +
            ", trades=" + trades +
            ", volume=" + volume +
            ", vwap=" + vwap +
            ", lastSize=" + lastSize +
            ", turnover=" + turnover +
            ", homeNotional=" + homeNotional +
            ", foreignNotional=" + foreignNotional +
            '}';
  }
}
