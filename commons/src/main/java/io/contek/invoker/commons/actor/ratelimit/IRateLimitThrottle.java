package io.contek.invoker.commons.actor.ratelimit;

import io.contek.invoker.ursa.core.api.AcquireTimeoutException;
import io.contek.invoker.ursa.core.api.IPermitSession;

import java.util.List;

public interface IRateLimitThrottle {

  IPermitSession acquire(String requestName, List<TypedPermitRequest> quota)
      throws AcquireTimeoutException, InterruptedException;
}
