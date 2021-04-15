package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestTickResponse<T> extends RestResponse {

  public T tick;
}
