package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _WithdrawPriority {

  public Double value;
  public String name;

  @Override
  public String toString() {
    return "_WithdrawPriority{" +
            "value=" + value +
            ", name='" + name + '\'' +
            '}';
  }
}
