package io.contek.invoker.hbdminverse.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class PaginatedData {

  public Integer total_page;
  public Integer current_page;
  public Integer total_size;
}
