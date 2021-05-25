package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

@ThreadSafe
public final class RateLimitCache {

  private final ImmutableMap<String, RuleBasedThrottle> rules;

  private RateLimitCache(ImmutableMap<String, RuleBasedThrottle> rules) {
    this.rules = rules;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  void acquire(String ruleName, String key, double permits) {
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
          rules.stream().collect(toImmutableMap(RateLimitRule::getName, RuleBasedThrottle::new)));
    }
  }

  @ThreadSafe
  private static final class RuleBasedThrottle {

    private final RateLimitRule rule;

    private final Map<String, Throttle> throttles = new HashMap<>();

    private RuleBasedThrottle(RateLimitRule rule) {
      this.rule = rule;
    }

    private void acquire(String key, double permits) {
      synchronized (throttles) {
        Throttle throttle = throttles.computeIfAbsent(key, this::createThrottle);
        throttle.acquire(permits);
      }
    }

    private Throttle createThrottle(String key) {
      return Throttle.fromRateLimitRule(key, rule);
    }
  }
}
