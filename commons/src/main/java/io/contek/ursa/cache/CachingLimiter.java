package io.contek.ursa.cache;

import io.contek.invoker.ursa.core.api.*;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CachingLimiter {
  private final RateLimit limit;

  private final Map<String, SlidingLimiter> map = Collections.synchronizedMap(new HashMap<>());

  CachingLimiter(RateLimit limit) {
    this.limit = limit;
  }

  IPermitSession acquire(String key, int permits, Duration timeout)
      throws PermitCapExceedException, AcquireTimeoutException, InterruptedException {
    SlidingLimiter limiter = this.map.computeIfAbsent(key, k -> create());
    return limiter.acquire(permits, timeout);
  }

  private SlidingLimiter create() {
    return new SlidingLimiter(this.limit);
  }
}
