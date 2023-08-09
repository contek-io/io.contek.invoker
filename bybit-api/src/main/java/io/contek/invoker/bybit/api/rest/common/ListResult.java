package io.contek.invoker.bybit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class ListResult<T> {

  public List<T> list;
}
