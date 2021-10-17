package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.base.Joiner;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;

@ThreadSafe
final class Throttle {

  private static final double MULTIPLIER = 10_000;

  private final RateLimiter limiter;

  private Throttle(RateLimiter limiter) {
    this.limiter = limiter;
  }

  void acquire(double permits) {
    limiter.acquirePermission((int) (permits * MULTIPLIER));
  }

  static Throttle fromRateLimitRule(String key, RateLimitRule rule, RateLimitCushion cushion) {
    Duration cushionedRefreshPeriod = cushion.getCushionedRefreshPeriod(rule.getResetPeriod());
    return new Throttle(
        RateLimiter.of(
            Joiner.on('_').join(rule.getType(), rule.getName(), key),
            RateLimiterConfig.custom()
                .limitForPeriod((int) (rule.getMaxPermits() * MULTIPLIER))
                .limitRefreshPeriod(cushionedRefreshPeriod)
                .timeoutDuration(rule.getResetPeriod())
                .build()));
  }
}
