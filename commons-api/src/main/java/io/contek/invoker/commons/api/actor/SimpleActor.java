package io.contek.invoker.commons.api.actor;

import io.contek.invoker.commons.api.actor.http.IHttpClient;
import io.contek.invoker.commons.api.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;

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
  public IHttpClient getHttpClient() {
    return httpClient;
  }

  @Override
  public IRateLimitThrottle getRateLimitThrottle() {
    return rateLimitThrottle;
  }

  @Override
  public Clock getClock() {
    return Clock.systemUTC();
  }
}
