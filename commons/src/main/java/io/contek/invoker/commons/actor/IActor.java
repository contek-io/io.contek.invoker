package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.vertx.core.Vertx;

import java.time.Clock;
import java.util.List;

public interface IActor {

  ICredential credential();

  RequestContext requestContext(String requestName, List<TypedPermitRequest> quota)
      throws AcquireTimeoutException, InterruptedException;

  Clock clock();

  IHttpClient httpClient();

  Vertx vertx();

  void runOnContext(Runnable runnable);
}
