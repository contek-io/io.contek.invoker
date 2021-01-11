package io.contek.invoker.deribit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestResponse<T> {

  public Boolean success;
  public T result;
}
