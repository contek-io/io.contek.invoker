package io.contek.invoker.ftx.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestError extends RestResponse<Object> {

  public String error;
}
