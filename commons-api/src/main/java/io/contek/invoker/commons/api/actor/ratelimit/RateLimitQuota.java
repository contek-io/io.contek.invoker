package io.contek.invoker.commons.api.actor.ratelimit;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RateLimitQuota {

  private final String ruleName;
  private final RateLimitType type;
  private final int permits;

  RateLimitQuota(String ruleName, RateLimitType type, int permits) {
    this.ruleName = ruleName;
    this.type = type;
    this.permits = permits;
  }

  String getRuleName() {
    return ruleName;
  }

  RateLimitType getType() {
    return type;
  }

  int getPermits() {
    return permits;
  }
}
