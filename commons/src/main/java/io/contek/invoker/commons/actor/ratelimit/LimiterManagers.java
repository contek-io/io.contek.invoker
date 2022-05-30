package io.contek.invoker.commons.actor.ratelimit;

import io.contek.ursa.cache.LimiterManager;

public final class LimiterManagers {

  private LimiterManagers() {}

  public static LimiterManager forRules(RateLimitRule... rules) {
    LimiterManager.Builder builder = LimiterManager.newBuilder();
    for (RateLimitRule rule : rules) {
      builder.addRateLimit(rule.name(), rule.limit());
    }
    return builder.build();
  }
}
