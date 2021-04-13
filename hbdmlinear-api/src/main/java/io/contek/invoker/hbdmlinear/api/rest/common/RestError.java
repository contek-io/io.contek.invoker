package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestError extends RestResponse {

  public int err_code;
  public String err_msg;
}
