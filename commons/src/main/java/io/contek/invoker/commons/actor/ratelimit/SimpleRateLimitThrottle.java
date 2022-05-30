package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.contek.invoker.ursa.core.api.IPermitSession;
import io.contek.ursa.cache.LimiterManager;
import io.contek.ursa.cache.PermitRequest;

import java.time.Duration;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.ImmutableList.toImmutableList;

public record SimpleRateLimitThrottle(String boundLocalAddress, String apiKeyId, LimiterManager manager,
                                      ImmutableList<IRateLimitQuotaInterceptor> interceptors) implements IRateLimitThrottle {

  private static final Duration TIMEOUT = Duration.ofMinutes(1);

  @Override
  public IPermitSession acquire(String requestName, List<TypedPermitRequest> quota)
          throws AcquireTimeoutException, InterruptedException {
    for (IRateLimitQuotaInterceptor interceptor : interceptors) {
      quota = interceptor.apply(requestName, quota);
    }
    List<PermitRequest> requests =
            quota.stream().map(this::toPermitRequest).collect(toImmutableList());
    return manager.acquire(requests);
  }

  private PermitRequest toPermitRequest(TypedPermitRequest quota) {
    checkArgument(quota.permits() > 0);

    return switch (quota.type()) {
      case IP -> {
        yield PermitRequest.newBuilder()
                .setName(quota.name())
                .setKey(boundLocalAddress)
                .setPermits(quota.permits())
                .setTimeout(TIMEOUT)
                .build();
      }
      case API_KEY -> {
        if (apiKeyId == null) {
          throw new IllegalArgumentException();
        }
        yield PermitRequest.newBuilder()
                .setName(quota.name())
                .setKey(apiKeyId)
                .setPermits(quota.permits())
                .setTimeout(TIMEOUT)
                .build();
      }
    };
  }
}
