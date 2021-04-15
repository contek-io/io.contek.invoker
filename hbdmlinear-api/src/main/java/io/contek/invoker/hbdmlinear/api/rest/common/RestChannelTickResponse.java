package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestChannelTickResponse<T> extends RestTickResponse<T> {

  public String ch;
}
