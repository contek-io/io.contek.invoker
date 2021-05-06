package io.contek.invoker.hbdminverse.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestDataResponse<T> extends RestResponse {

  public T data;
}
