package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestError {

  public Integer err_code;
  public String err_msg;
}
