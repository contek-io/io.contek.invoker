package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _LendingRates implements Serializable {

  public String coin;
  public Double estimate;
  public Double previous;

  @Override
  public String toString() {
    return "_LendingRates{"
        + "coin='"
        + coin
        + '\''
        + ", estimate="
        + estimate
        + ", previous="
        + previous
        + '}';
  }
}
