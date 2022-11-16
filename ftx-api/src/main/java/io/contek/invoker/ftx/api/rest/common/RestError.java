package io.contek.invoker.ftx.api.rest.common;

import io.contek.invoker.commons.rest.Empty;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestError extends RestResponse<Empty> {

  public String error;
}
