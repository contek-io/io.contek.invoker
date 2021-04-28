package io.contek.invoker.hbdmlinear.api.rest.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class RestTicksResponse<T> extends RestResponse {

  public List<T> ticks;
}
