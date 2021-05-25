package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Nullable;

import javax.annotation.concurrent.ThreadSafe;
import java.net.InetAddress;
import java.util.List;

@ThreadSafe
public final class SimpleRateLimitThrottleFactory implements IRateLimitThrottleFactory {

  private final RateLimitCache cache;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  private SimpleRateLimitThrottleFactory(
      RateLimitCache cache, ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.cache = cache;
    this.interceptors = interceptors;
  }

  public static SimpleRateLimitThrottleFactory create(
      RateLimitCache cache, List<IRateLimitQuotaInterceptor> interceptors) {
    return new SimpleRateLimitThrottleFactory(cache, ImmutableList.copyOf(interceptors));
  }

  @Override
  public IRateLimitThrottle create(InetAddress boundLocalAddress, @Nullable String apiKeyId) {
    return new SimpleRateLimitThrottle(
        boundLocalAddress.getCanonicalHostName(), apiKeyId, cache, interceptors);
  }
}
