package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class _OpenInterestStatistics {

  public String symbol;
  public Double sumOpenInterest;
  public Double sumOpenInterestValue;
  public Long timestamp;

  @Override
  public String toString() {
    return "_OpenInterestStatistics{"
        + "symbol='"
        + symbol
        + '\''
        + ", sumOpenInterest="
        + sumOpenInterest
        + ", sumOpenInterestValue="
        + sumOpenInterestValue
        + ", timestamp="
        + timestamp
        + '}';
  }
}
