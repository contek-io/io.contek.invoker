package io.contek.invoker.hbdminverse.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestError {

  public Integer err_code;
  public String err_msg;
}
