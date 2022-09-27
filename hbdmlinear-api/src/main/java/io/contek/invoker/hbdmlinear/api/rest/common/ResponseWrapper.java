package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class ResponseWrapper {

  public Integer err_code;
  public String err_msg;
}
