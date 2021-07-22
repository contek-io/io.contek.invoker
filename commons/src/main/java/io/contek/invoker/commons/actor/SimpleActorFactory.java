package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.http.IHttpClientFactory;
import io.contek.invoker.commons.actor.http.IHttpContext;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottleFactory;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.security.ICredentialFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class SimpleActorFactory implements IActorFactory {

  private final ICredentialFactory credentialFactory;
  private final IHttpClientFactory httpClientFactory;
  private final IRateLimitThrottleFactory rateLimitThrottleFactory;

  private SimpleActorFactory(
      ICredentialFactory credentialFactory,
      IHttpClientFactory httpClientFactory,
      IRateLimitThrottleFactory rateLimitThrottleFactory) {
    this.credentialFactory = credentialFactory;
    this.httpClientFactory = httpClientFactory;
    this.rateLimitThrottleFactory = rateLimitThrottleFactory;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public IActor create(@Nullable ApiKey apiKey, IHttpContext context) {
    ICredential credential =
        apiKey == null ? ICredential.anonymous() : credentialFactory.create(apiKey);
    IHttpClient httpClient = httpClientFactory.create(context);
    IRateLimitThrottle rateLimitThrottle =
        rateLimitThrottleFactory.create(
            httpClient.getBoundLocalAddress(), apiKey == null ? null : apiKey.getId());
    return new SimpleActor(credential, httpClient, rateLimitThrottle);
  }

  @NotThreadSafe
  public static final class Builder {

    private ICredentialFactory credentialFactory;
    private IHttpClientFactory httpClientFactory;
    private IRateLimitThrottleFactory rateLimitThrottleFactory;

    private Builder() {}

    public Builder setCredentialFactory(ICredentialFactory credentialFactory) {
      this.credentialFactory = credentialFactory;
      return this;
    }

    public Builder setHttpClientFactory(IHttpClientFactory httpClientFactory) {
      this.httpClientFactory = httpClientFactory;
      return this;
    }

    public Builder setRateLimitThrottleFactory(IRateLimitThrottleFactory rateLimitThrottleFactory) {
      this.rateLimitThrottleFactory = rateLimitThrottleFactory;
      return this;
    }

    public SimpleActorFactory build() {
      if (credentialFactory == null) {
        throw new IllegalArgumentException("No credential factory specified");
      }
      if (httpClientFactory == null) {
        throw new IllegalArgumentException("No http client factory specified");
      }
      if (rateLimitThrottleFactory == null) {
        throw new IllegalArgumentException("No rate limit throttle factory specified");
      }
      return new SimpleActorFactory(credentialFactory, httpClientFactory, rateLimitThrottleFactory);
    }
  }
}
