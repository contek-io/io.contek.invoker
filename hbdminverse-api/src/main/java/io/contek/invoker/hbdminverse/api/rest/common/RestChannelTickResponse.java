package io.contek.invoker.hbdminverse.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class RestChannelTickResponse<T> extends RestTickResponse<T> {

  public String ch;
}
