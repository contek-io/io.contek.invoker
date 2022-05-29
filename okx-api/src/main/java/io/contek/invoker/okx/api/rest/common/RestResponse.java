package io.contek.invoker.okx.api.rest.common;

import java.util.List;

public abstract class RestResponse<T> {

  public String code;
  public String msg;
  public List<T> data;
}
