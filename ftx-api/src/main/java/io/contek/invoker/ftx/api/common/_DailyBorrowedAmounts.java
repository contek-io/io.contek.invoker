package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _DailyBorrowedAmounts implements Serializable {

  public String coin;
  public Double size;

  @Override
  public String toString() {
    return "_DailyBorrowedAmounts{" + "coin='" + coin + '\'' + ", size=" + size + '}';
  }
}
