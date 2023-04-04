package io.contek.invoker.binancelinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestUpdateResponse {

  public int code;
  public String msg;
}
