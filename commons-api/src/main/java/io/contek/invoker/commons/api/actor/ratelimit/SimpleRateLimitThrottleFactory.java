package io.contek.invoker.commons.api.actor.ratelimit;

import javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.Nullable;

import java.net.InetAddress;

@Immutable
public final class SimpleRateLimitThrottleFactory implements IRateLimitThrottleFactory {

  private final RateLimitCache cache;

  private SimpleRateLimitThrottleFactory(RateLimitCache cache) {
    this.cache = cache;
  }

  public static SimpleRateLimitThrottleFactory fromCache(RateLimitCache cache) {
    return new SimpleRateLimitThrottleFactory(cache);
  }

  @Override
  public IRateLimitThrottle create(InetAddress boundLocalAddress, @Nullable String apiKeyId) {
    return new SimpleRateLimitThrottle(boundLocalAddress.getCanonicalHostName(), apiKeyId, cache);
  }
}
