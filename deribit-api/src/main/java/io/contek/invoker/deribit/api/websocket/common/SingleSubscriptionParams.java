package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class SingleSubscriptionParams<T> extends Params {

  public String channel;
  public T data;
}
