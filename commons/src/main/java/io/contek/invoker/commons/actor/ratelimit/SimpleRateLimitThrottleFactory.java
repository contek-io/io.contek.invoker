package io.contek.invoker.commons.actor.ratelimit;

import org.jetbrains.annotations.Nullable;

import javax.annotation.concurrent.Immutable;
import java.net.InetAddress;

@Immutable
public final class SimpleRateLimitThrottleFactory implements IRateLimitThrottleFactory {

  private final RateLimitCache cache;
  private final IRateLimitQuotaInterceptor interceptor;

  private SimpleRateLimitThrottleFactory(
      RateLimitCache cache, @Nullable IRateLimitQuotaInterceptor interceptor) {
    this.cache = cache;
    this.interceptor = interceptor;
  }

  public static SimpleRateLimitThrottleFactory create(
      RateLimitCache cache, @Nullable IRateLimitQuotaInterceptor interceptor) {
    return new SimpleRateLimitThrottleFactory(cache, interceptor);
  }

  @Override
  public IRateLimitThrottle create(InetAddress boundLocalAddress, @Nullable String apiKeyId) {
    return new SimpleRateLimitThrottle(
        boundLocalAddress.getCanonicalHostName(), apiKeyId, cache, interceptor);
  }
}
