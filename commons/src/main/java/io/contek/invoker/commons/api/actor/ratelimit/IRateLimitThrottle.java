package io.contek.invoker.commons.api.actor.ratelimit;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public interface IRateLimitThrottle {

  void acquire(String requestName, List<RateLimitQuota> quota);
}
