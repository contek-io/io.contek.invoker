package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OpenInterest {

  public String symbol;
  public Double openInterest;
  public Long time;

  @Override
  public String toString() {
    return "_OpenInterest{"
        + "symbol='"
        + symbol
        + '\''
        + ", openInterest="
        + openInterest
        + ", time="
        + time
        + '}';
  }
}
