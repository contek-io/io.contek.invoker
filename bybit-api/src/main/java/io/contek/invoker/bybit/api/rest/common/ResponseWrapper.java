package io.contek.invoker.bybit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class ResponseWrapper<R> {

  public Integer ret_code;
  public String ret_msg;
  public String ext_code;
  public String ext_info;
  public Double time_now;
  public Integer rate_limit_status;
  public Long rate_limit_reset_ms;
  public Integer rate_limit;
  public R result;
}
