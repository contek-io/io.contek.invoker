package io.contek.invoker.commons.actor.ratelimit;

import io.contek.ursa.PermittedSession;
import io.contek.ursa.Ursa;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;

@ThreadSafe
final class Throttle {

  private final Ursa limiter;

  private Throttle(Ursa limiter) {
    this.limiter = limiter;
  }

  PermittedSession acquire(int permits) throws InterruptedException {
    return limiter.acquire(permits);
  }

  static Throttle fromRateLimitRule(RateLimitRule rule, RateLimitCushion cushion) {
    Duration cushionedRefreshPeriod = cushion.getCushionedRefreshPeriod(rule.getResetPeriod());
    return new Throttle(new Ursa(rule.getMaxPermits(), cushionedRefreshPeriod));
  }
}
