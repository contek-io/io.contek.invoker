package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class RateLimitCushion {

  private final double additionalProportionalDuration;
  private final Duration additionalAbsoluteDuration;

  private RateLimitCushion(double additionalResetPercentage, Duration additionalResetDuration) {
    this.additionalProportionalDuration = additionalResetPercentage;
    this.additionalAbsoluteDuration = additionalResetDuration;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public double getAdditionalProportionalDuration() {
    return additionalProportionalDuration;
  }

  public Duration getAdditionalAbsoluteDuration() {
    return additionalAbsoluteDuration;
  }

  public Duration getCushionedRefreshPeriod(Duration refreshPeriod) {
    double refreshMillis = refreshPeriod.toMillis();
    double proportionMillis = refreshMillis * additionalProportionalDuration;
    double absoluteMillis = additionalAbsoluteDuration.toMillis();
    double total = refreshMillis + proportionMillis + absoluteMillis;
    return Duration.ofMillis((long) total);
  }

  @NotThreadSafe
  public static final class Builder {

    private Double additionalResetPercentage;
    private Duration additionalResetDuration;

    private Builder() {}

    public Builder setAdditionalResetPercentage(Double additionalResetPercentage) {
      this.additionalResetPercentage = additionalResetPercentage;
      return this;
    }

    public Builder setAdditionalResetDuration(Duration additionalResetDuration) {
      this.additionalResetDuration = additionalResetDuration;
      return this;
    }

    public RateLimitCushion build() {
      checkNotNull(additionalResetPercentage);
      checkNotNull(additionalResetDuration);
      return new RateLimitCushion(additionalResetPercentage, additionalResetDuration);
    }
  }
}
