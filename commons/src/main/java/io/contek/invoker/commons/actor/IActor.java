package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.ursa.core.api.AcquireTimeoutException;

import java.time.Clock;
import java.util.List;

public interface IActor {

  ICredential getCredential();

  RequestContext getRequestContext(String requestName, List<TypedPermitRequest> quota)
      throws AcquireTimeoutException, InterruptedException;

  Clock getClock();
}
