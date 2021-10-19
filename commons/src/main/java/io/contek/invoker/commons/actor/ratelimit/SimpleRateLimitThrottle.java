package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import io.contek.ursa.CombinePermitSession;
import io.contek.ursa.IPermitSession;
import io.contek.ursa.cache.LimiterManager;
import io.contek.ursa.cache.PermitRequest;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.ImmutableList.toImmutableList;

@ThreadSafe
public final class SimpleRateLimitThrottle implements IRateLimitThrottle {

  private final String boundLocalAddress;
  private final String apiKeyId;

  private final LimiterManager manager;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  SimpleRateLimitThrottle(
      String boundLocalAddress,
      @Nullable String apiKeyId,
      LimiterManager manager,
      ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.boundLocalAddress = boundLocalAddress;
    this.apiKeyId = apiKeyId;
    this.manager = manager;
    this.interceptors = interceptors;
  }

  @Override
  public IPermitSession acquire(String requestName, List<TypedPermitRequest> quota) {
    for (IRateLimitQuotaInterceptor interceptor : interceptors) {
      quota = interceptor.apply(requestName, quota);
    }
    List<IPermitSession> sessions = quota.stream().map(this::acquire).collect(toImmutableList());
    return CombinePermitSession.wrap(sessions);
  }

  private IPermitSession acquire(TypedPermitRequest quota) {
    checkArgument(quota.getPermits() > 0);

    switch (quota.getType()) {
      case IP:
        return manager.acquire(
            PermitRequest.newBuilder()
                .setName(quota.getName())
                .setKey(boundLocalAddress)
                .setPermits(quota.getPermits())
                .build());
      case API_KEY:
        if (apiKeyId == null) {
          throw new IllegalArgumentException();
        }
        return manager.acquire(
            PermitRequest.newBuilder()
                .setName(quota.getName())
                .setKey(apiKeyId)
                .setPermits(quota.getPermits())
                .build());
      default:
        throw new UnsupportedOperationException(quota.getType().name());
    }
  }
}
