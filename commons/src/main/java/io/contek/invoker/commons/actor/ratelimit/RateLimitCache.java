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

    private final List<RateLimitRule> rules = new ArrayList<>();

    public Builder addRule(RateLimitRule rule) {
      rules.add(rule);
      return this;
    }

    public RateLimitCache build() {
      return new RateLimitCache(
          rules.stream().collect(toImmutableMap(RateLimitRule::getName, RuleBasedLimiter::new)));
    }
  }

  @ThreadSafe
  private static final class RuleBasedLimiter {

    private final RateLimitRule rule;

    private final Map<String, RateLimiter> limiters = new HashMap<>();

    private RuleBasedLimiter(RateLimitRule rule) {
      this.rule = rule;
    }

    private void acquire(String key, int permits) {
      synchronized (limiters) {
        RateLimiter limiter = limiters.computeIfAbsent(key, rule::createRateLimiter);
        limiter.acquirePermission(permits);
      }
    }
  }
}
