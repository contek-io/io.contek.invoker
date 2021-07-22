package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _MarketStats {

  public String rootSymbol;
  public String currency;
  public Double volume24h;
  public Double turnover24h;
  public Double openInterest;
  public Double openValue;

  @Override
  public String toString() {
    return "_MarketStats{"
        + "rootSymbol='"
        + rootSymbol
        + '\''
        + ", currency='"
        + currency
        + '\''
        + ", volume24h="
        + volume24h
        + ", turnover24h="
        + turnover24h
        + ", openInterest="
        + openInterest
        + ", openValue="
        + openValue
        + '}';
  }
}
