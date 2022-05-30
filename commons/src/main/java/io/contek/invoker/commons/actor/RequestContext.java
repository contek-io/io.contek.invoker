package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.ursa.core.api.IPermitSession;

public record RequestContext(IHttpClient client, IPermitSession permit) implements AutoCloseable {

  @Override
  public void close() {
    permit.close();
  }
}
