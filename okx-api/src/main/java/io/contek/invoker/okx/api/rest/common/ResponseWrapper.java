package io.contek.invoker.okx.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class ResponseWrapper<T> {

  public String code;
  public String msg;
  public List<T> data;
}
