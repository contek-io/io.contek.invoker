package io.contek.invoker.commons.actor.ratelimit;

import io.contek.ursa.RateLimit;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class RateLimitRule {

  private final String name;
  private final LimitType type;
  private final RateLimit limit;

  private RateLimitRule(String name, LimitType type, RateLimit limit) {
    this.name = name;
    this.type = type;
    this.limit = limit;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public TypedPermitRequest forPermits(int permits) {
    return new TypedPermitRequest(name, type, permits);
  }

  public String getName() {
    return name;
  }

  public LimitType getType() {
    return type;
  }

  public RateLimit getLimit() {
    return limit;
  }

  @NotThreadSafe
  public static final class Builder {

    private String name;
    private LimitType type;
    private int maxPermits;
    private Duration resetPeriod;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setType(LimitType type) {
      this.type = type;
      return this;
    }

    public Builder setMaxPermits(int maxPermits) {
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
      return new RateLimitRule(name, type, new RateLimit(maxPermits, resetPeriod));
    }

    private Builder() {}
  }
}
