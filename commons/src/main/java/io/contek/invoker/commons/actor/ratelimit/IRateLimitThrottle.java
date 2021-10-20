package io.contek.invoker.commons.actor.ratelimit;

import io.contek.ursa.AcquireTimeoutException;
import io.contek.ursa.IPermitSession;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public interface IRateLimitThrottle {

  IPermitSession acquire(String requestName, List<TypedPermitRequest> quota)
      throws AcquireTimeoutException, InterruptedException;
}
