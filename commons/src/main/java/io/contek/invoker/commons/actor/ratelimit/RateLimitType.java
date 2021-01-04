package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum RateLimitType {
  IP,
  API_KEY
}
