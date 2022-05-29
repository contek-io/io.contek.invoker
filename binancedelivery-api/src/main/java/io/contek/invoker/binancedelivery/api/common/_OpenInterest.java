package io.contek.invoker.binancedelivery.api.common;

public class _OpenInterest {

  public String symbol;
  public String pair;
  public Double openInterest;
  public String contractType;
  public Long time;

  @Override
  public String toString() {
    return "_OpenInterest{" +
            "symbol='" + symbol + '\'' +
            ", pair='" + pair + '\'' +
            ", openInterest=" + openInterest +
            ", contractType='" + contractType + '\'' +
            ", time=" + time +
            '}';
  }
}
