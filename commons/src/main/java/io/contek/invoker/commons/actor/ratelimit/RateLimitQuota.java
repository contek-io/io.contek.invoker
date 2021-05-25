package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RateLimitQuota {

  private final String ruleName;
  private final RateLimitType type;
  private final double permits;

  public RateLimitQuota(String ruleName, RateLimitType type, double permits) {
    this.ruleName = ruleName;
    this.type = type;
    this.permits = permits;
  }

  public String getRuleName() {
    return ruleName;
  }

  public RateLimitType getType() {
    return type;
  }

  public double getPermits() {
    return permits;
  }
}
