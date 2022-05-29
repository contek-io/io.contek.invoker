package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.ursa.core.api.IPermitSession;

public final class RequestContext implements AutoCloseable {

  private final IHttpClient client;
  private final IPermitSession permit;

  public RequestContext(IHttpClient client, IPermitSession permit) {
    this.client = client;
    this.permit = permit;
  }

  public IHttpClient getClient() {
    return client;
  }

  @Override
  public void close() {
    permit.close();
  }
}
