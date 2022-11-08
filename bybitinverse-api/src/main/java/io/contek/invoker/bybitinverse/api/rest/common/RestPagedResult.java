package io.contek.invoker.bybitinverse.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class RestPagedResult<T> {

  public int current_page;
  public int last_page;
  public List<T> data;
}
