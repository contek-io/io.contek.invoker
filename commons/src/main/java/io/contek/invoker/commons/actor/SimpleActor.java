package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.contek.invoker.ursa.core.api.IPermitSession;
import io.vertx.core.Vertx;

import java.time.Clock;
import java.util.List;

public record SimpleActor(ICredential credential, IHttpClient httpClient,
                          IRateLimitThrottle rateLimitThrottle, Vertx vertx) implements IActor {

  @Override
  public RequestContext requestContext(String requestName, List<TypedPermitRequest> quota)
          throws AcquireTimeoutException, InterruptedException {
    IPermitSession session = rateLimitThrottle.acquire(requestName, quota);
    return new RequestContext(httpClient, session);
  }

  @Override
  public Clock clock() {
    return Clock.systemUTC();
  }

  @Override
  public void runOnContext(Runnable runnable) {
    vertx.runOnContext(event -> runnable.run());
  }
}
