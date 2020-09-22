package io.contek.invoker.commons.api.actor.ratelimit;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static com.google.common.base.Preconditions.checkArgument;

@ThreadSafe
public final class SimpleRateLimitThrottle implements IRateLimitThrottle {

  private final String boundLocalAddress;
  private final String apiKeyId;

  private final RateLimitCache cache;

  SimpleRateLimitThrottle(
      String boundLocalAddress, @Nullable String apiKeyId, RateLimitCache cache) {
    this.boundLocalAddress = boundLocalAddress;
    this.apiKeyId = apiKeyId;
    this.cache = cache;
  }

  public void acquire(RateLimitQuota quota) {
    checkArgument(quota.getPermits() > 0);

    switch (quota.getType()) {
      case IP:
        cache.acquire(quota.getRuleName(), boundLocalAddress, quota.getPermits());
        break;
      case API_KEY:
        if (apiKeyId == null) {
          throw new IllegalArgumentException();
        }
        cache.acquire(quota.getRuleName(), apiKeyId, quota.getPermits());
        break;
      default:
        throw new UnsupportedOperationException(quota.getType().name());
    }
  }
}
