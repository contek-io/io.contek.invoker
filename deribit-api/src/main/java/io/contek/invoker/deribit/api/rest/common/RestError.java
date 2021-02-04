package io.contek.invoker.deribit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestError extends RestResponse<Void> {

  public String error;
}
