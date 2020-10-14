package io.contek.invoker.bybit.api.rest.common;

import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestPagedResult<T> {

  public int current_page;
  public int last_page;
  public List<T> data;
}
