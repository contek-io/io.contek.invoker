package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import io.contek.ursa.cache.LimiterManager;

import java.net.InetAddress;
import java.util.List;

public record SimpleRateLimitThrottleFactory(LimiterManager manager,
                                             ImmutableList<IRateLimitQuotaInterceptor> interceptors) implements IRateLimitThrottleFactory {

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
