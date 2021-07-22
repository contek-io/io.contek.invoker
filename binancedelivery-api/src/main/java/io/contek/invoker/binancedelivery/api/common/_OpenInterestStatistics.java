package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class _OpenInterestStatistics {

  public String pair;
  public String contractType;
  public Double sumOpenInterest;
  public Double sumOpenInterestValue;
  public Long timestamp;

  @Override
  public String toString() {
    return "_OpenInterestStatistics{"
        + "pair='"
        + pair
        + '\''
        + ", contractType='"
        + contractType
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
