package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import io.contek.ursa.PermittedSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.ImmutableList.toImmutableList;

@ThreadSafe
public final class SimpleRateLimitThrottle implements IRateLimitThrottle {

  private final String boundLocalAddress;
  private final String apiKeyId;

  private final RateLimitCache cache;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  SimpleRateLimitThrottle(
      String boundLocalAddress,
      @Nullable String apiKeyId,
      RateLimitCache cache,
      ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.boundLocalAddress = boundLocalAddress;
    this.apiKeyId = apiKeyId;
    this.cache = cache;
    this.interceptors = interceptors;
  }

  public ImmutableList<PermittedSession> acquire(String requestName, List<RateLimitQuota> quota) {
    for (IRateLimitQuotaInterceptor interceptor : interceptors) {
      quota = interceptor.apply(requestName, quota);
    }
    return quota.stream().map(this::acquire).collect(toImmutableList());
  }

  private PermittedSession acquire(RateLimitQuota quota) throws InterruptedException {
    checkArgument(quota.getPermits() > 0);

    switch (quota.getType()) {
      case IP:
        return cache.acquire(quota.getRuleName(), boundLocalAddress, quota.getPermits());
      case API_KEY:
        if (apiKeyId == null) {
          throw new IllegalArgumentException();
        }
        return cache.acquire(quota.getRuleName(), apiKeyId, quota.getPermits());
      default:
        throw new UnsupportedOperationException(quota.getType().name());
    }
  }
}
