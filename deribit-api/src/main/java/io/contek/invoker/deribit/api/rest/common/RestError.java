package io.contek.invoker.deribit.api.rest.common;

import io.contek.invoker.deribit.api.common._Error;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestError extends RestResponse<Object> {
  public _Error error;
}
