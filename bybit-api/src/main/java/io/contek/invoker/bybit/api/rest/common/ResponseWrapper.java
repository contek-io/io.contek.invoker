package io.contek.invoker.bybit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class ResponseWrapper<R> {

  public Integer retCode;
  public String retMsg;
  public String retExtInfo;
  public Long time;
  public R result;
}
