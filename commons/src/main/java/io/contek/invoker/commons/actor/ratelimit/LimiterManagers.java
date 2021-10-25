package io.contek.invoker.commons.actor.ratelimit;

import io.contek.ursa.cache.LimiterManager;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class LimiterManagers {

  public static LimiterManager forRules(RateLimitRule... rules) {
    LimiterManager.Builder builder = LimiterManager.newBuilder();
    for (RateLimitRule rule : rules) {
      builder.addRateLimit(rule.getName(), rule.getLimit());
    }
    return builder.build();
  }

  private LimiterManagers() {}
}
