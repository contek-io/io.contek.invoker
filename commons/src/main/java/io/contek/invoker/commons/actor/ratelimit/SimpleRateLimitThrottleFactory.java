package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import io.contek.ursa.cache.LimiterManager;

import java.net.InetAddress;
import java.util.List;

public final class SimpleRateLimitThrottleFactory implements IRateLimitThrottleFactory {

  private final LimiterManager manager;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  private SimpleRateLimitThrottleFactory(
      LimiterManager manager, ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.manager = manager;
    this.interceptors = interceptors;
  }

  public static SimpleRateLimitThrottleFactory create(
      LimiterManager cache, List<IRateLimitQuotaInterceptor> interceptors) {
    return new SimpleRateLimitThrottleFactory(cache, ImmutableList.copyOf(interceptors));
  }

  @Override
  public IRateLimitThrottle create(InetAddress boundLocalAddress, String apiKeyId) {
    return new SimpleRateLimitThrottle(
        boundLocalAddress.getCanonicalHostName(), apiKeyId, manager, interceptors);
  }
}
