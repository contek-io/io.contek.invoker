package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.base.Joiner;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class RateLimitRule {

  static final double MULTIPLIER = 10_000;

  private final String name;
  private final RateLimitType type;
  private final double maxPermits;
  private final Duration resetPeriod;

  private RateLimitRule(String name, RateLimitType type, double maxPermits, Duration resetPeriod) {
    this.name = name;
    this.type = type;
    this.maxPermits = maxPermits;
    this.resetPeriod = resetPeriod;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public RateLimitQuota createRateLimitQuota(int permits) {
    return new RateLimitQuota(name, type, permits);
  }

  public String getName() {
    return name;
  }

  public RateLimitType getType() {
    return type;
  }

  public double getMaxPermits() {
    return maxPermits;
  }

  public Duration getResetPeriod() {
    return resetPeriod;
  }

  RateLimiter createRateLimiter(String key) {
    return RateLimiter.of(
        Joiner.on('_').join(type, name, key),
        RateLimiterConfig.custom()
            .limitForPeriod((int) (maxPermits * MULTIPLIER))
            .limitRefreshPeriod(resetPeriod)
            .timeoutDuration(resetPeriod)
            .build());
  }

  @NotThreadSafe
  public static final class Builder {

    private String name;
    private RateLimitType type;
    private double maxPermits;
    private Duration resetPeriod;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setType(RateLimitType type) {
      this.type = type;
      return this;
    }

    public Builder setMaxPermits(double maxPermits) {
      this.maxPermits = maxPermits;
      return this;
    }

    public Builder setResetPeriod(Duration resetPeriod) {
      this.resetPeriod = resetPeriod;
      return this;
    }

    public RateLimitRule build() {
      if (name == null) {
        throw new IllegalArgumentException("No rule name specified");
      }
      if (type == null) {
        throw new IllegalArgumentException("No rule type specified");
      }
      if (maxPermits <= 0) {
        throw new IllegalArgumentException("Invalid max permits");
      }
      if (resetPeriod == null) {
        throw new IllegalArgumentException("No reset period specified");
      }
      return new RateLimitRule(name, type, maxPermits, resetPeriod);
    }

    private Builder() {}
  }
}
