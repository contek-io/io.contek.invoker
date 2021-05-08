package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Params<T> {

  public String channel;
  public T data;
}
