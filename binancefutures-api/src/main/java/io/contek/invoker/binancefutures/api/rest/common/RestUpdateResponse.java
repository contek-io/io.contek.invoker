package io.contek.invoker.binancefutures.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestUpdateResponse {

  public int code;
  public String msg;
}
