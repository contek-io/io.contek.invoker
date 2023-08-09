package io.contek.invoker.bybit.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class PageListResult<T> extends ListResult<T> {

  public String nextPageCursor;
}
