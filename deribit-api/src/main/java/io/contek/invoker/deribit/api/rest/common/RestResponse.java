package io.contek.invoker.deribit.api.rest.common;

public abstract class RestResponse<T> {

  public String jsonrpc;
  public T result;
}
