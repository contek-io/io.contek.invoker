package io.contek.invoker.deribit.api.websocket.common;

public final class SingleSubscriptionParams<T> extends Params {

  public String channel;
  public T data;
}
