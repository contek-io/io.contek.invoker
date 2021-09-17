package io.contek.invoker.ftx.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class PaginationRestResponse<T> extends RestResponse<T> {

  public Boolean hasMoreData;
}
