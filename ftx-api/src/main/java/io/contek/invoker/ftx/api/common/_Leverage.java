package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _Leverage implements Serializable {

  public Double leverage;

  @Override
  public String toString() {
    return "_Leverage{" + "leverage=" + leverage + '}';
  }
}
