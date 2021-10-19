package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.ursa.IPermitSession;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.List;

@ThreadSafe
public final class SimpleActor implements IActor {

  private final ICredential credential;
  private final IHttpClient httpClient;
  private final IRateLimitThrottle rateLimitThrottle;

  public SimpleActor(
      ICredential credential, IHttpClient httpClient, IRateLimitThrottle rateLimitThrottle) {
    this.credential = credential;
    this.httpClient = httpClient;
    this.rateLimitThrottle = rateLimitThrottle;
  }

  @Override
  public ICredential getCredential() {
    return credential;
  }

  @Override
  public RequestContext getRequestContext(String requestName, List<TypedPermitRequest> quota) {
    IPermitSession session = rateLimitThrottle.acquire(requestName, quota);
    return new RequestContext(httpClient, session);
  }

  @Override
  public Clock getClock() {
    return Clock.systemUTC();
  }
}
