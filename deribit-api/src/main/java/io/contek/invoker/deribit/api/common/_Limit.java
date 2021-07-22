package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Limit {

  public int rate;
  public int burst;

  @Override
  public String toString() {
    return "_Limit{" + "rate=" + rate + ", burst=" + burst + '}';
  }
}
