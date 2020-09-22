package io.contek.invoker.commons.api.actor.ratelimit;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IRateLimitThrottle {

  void acquire(RateLimitQuota quota);
}
