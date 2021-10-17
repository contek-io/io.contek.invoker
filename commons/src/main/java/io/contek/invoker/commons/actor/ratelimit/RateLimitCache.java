package io.contek.invoker.commons.actor.ratelimit;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
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

    private RateLimitCushion cushion;
    private final List<RateLimitRule> rules = new ArrayList<>();

    public Builder setCushion(RateLimitCushion cushion) {
      this.cushion = cushion;
      return this;
    }

    public Builder addRule(RateLimitRule rule) {
      rules.add(rule);
      return this;
    }

    public RateLimitCache build() {
      checkNotNull(cushion);

      return new RateLimitCache(
          rules.stream()
              .collect(
                  toImmutableMap(
                      RateLimitRule::getName, rule -> new RuleBasedThrottle(rule, cushion))));
    }
  }

  @ThreadSafe
  private static final class RuleBasedThrottle {

    private final RateLimitRule rule;
    private final RateLimitCushion cushion;

    private final Map<String, Throttle> throttles = new HashMap<>();

    private RuleBasedThrottle(RateLimitRule rule, RateLimitCushion cushion) {
      this.rule = rule;
      this.cushion = cushion;
    }

    private void acquire(String key, double permits) {
      synchronized (throttles) {
        Throttle throttle = throttles.computeIfAbsent(key, this::createThrottle);
        throttle.acquire(permits);
      }
    }

    private Throttle createThrottle(String key) {
      return Throttle.fromRateLimitRule(key, rule, cushion);
    }
  }
}
