package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.List;

@ThreadSafe
public interface IActor {

  ICredential getCredential();

  RequestContext getRequestContext(String requestName, List<TypedPermitRequest> quota);

  Clock getClock();
}
