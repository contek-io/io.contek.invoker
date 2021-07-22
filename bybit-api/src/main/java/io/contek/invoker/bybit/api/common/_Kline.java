package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Kline {

  public String symbol;
  public String interval;
  public long open_time;
  public double open;
  public double high;
  public double low;
  public double close;
  public double volume;
  public double turnover;

  @Override
  public String toString() {
    return "_Kline{"
        + "symbol='"
        + symbol
        + '\''
        + ", interval='"
        + interval
        + '\''
        + ", open_time="
        + open_time
        + ", open="
        + open
        + ", high="
        + high
        + ", low="
        + low
        + ", close="
        + close
        + ", volume="
        + volume
        + ", turnover="
        + turnover
        + '}';
  }
}
