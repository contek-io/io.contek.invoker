package io.contek.invoker.hbdminverse.api.common;

public class _Kline {

  public long id;
  public long mrid;
  public double open;
  public double close;
  public double high;
  public double low;
  public double amount;
  public double vol;
  public double trade_turnover;
  public double count;

  @Override
  public String toString() {
    return "_Kline{" +
            "id=" + id +
            ", mrid=" + mrid +
            ", open=" + open +
            ", close=" + close +
            ", high=" + high +
            ", low=" + low +
            ", amount=" + amount +
            ", vol=" + vol +
            ", trade_turnover=" + trade_turnover +
            ", count=" + count +
            '}';
  }
}
