package io.contek.invoker.hbdminverse.api.rest.common;

import java.util.List;

public abstract class RestTicksResponse<T> extends RestResponse {

  public List<T> ticks;
}
