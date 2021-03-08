package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableMap;
import io.github.resilience4j.ratelimiter.RateLimiter;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.toImmutableMap;
import static io.github.resilience4j.ratelimiter.RateLimiter.waitForPermission;

@ThreadSafe
public final class RateLimitCache {

  private final ImmutableMap<String, RuleBasedLimiter> rules;

  private RateLimitCache(ImmutableMap<String, RuleBasedLimiter> rules) {
    this.rules = rules;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  void acquire(String ruleName, String key, int permits) {
    rules.get(ruleName).acquire(key, permits);
  }

  @NotThreadSafe
  public static final class Builder {

    private double cushion = 0.1;
    private final List<RateLimitRule> rules = new ArrayList<>();

    public Builder setCushion(double cushion) {
      this.cushion = cushion;
      return this;
    }

    public Builder addRule(RateLimitRule rule) {
      rules.add(rule);
      return this;
    }

    public RateLimitCache build() {
      return new RateLimitCache(
          rules.stream()
              .collect(
                  toImmutableMap(
                      RateLimitRule::getName, rule -> new RuleBasedLimiter(rule, cushion))));
    }
  }

  @ThreadSafe
  private static final class RuleBasedLimiter {

    private final RateLimitRule rule;
    private final double cushion;

    private final Map<String, RateLimiter> limiters = new HashMap<>();

    private RuleBasedLimiter(RateLimitRule rule, double cushion) {
      this.rule = rule;
      this.cushion = cushion;
    }

    private void acquire(String key, int permits) {
      synchronized (limiters) {
        RateLimiter limiter = limiters.computeIfAbsent(key, this::createRateLimiter);
        waitForPermission(limiter, permits);
      }
    }

    private RateLimiter createRateLimiter(String key) {
      return rule.createRateLimiter(key, cushion);
    }
  }
}
