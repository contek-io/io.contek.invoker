package io.contek.invoker.bybitlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestResponse<R> {

  public int ret_code;
  public String ret_msg;
  public String ext_code;
  public String ext_info;
  public double time_now;
  public int rate_limit_status;
  public long rate_limit_reset_ms;
  public int rate_limit;
  public R result;
}
