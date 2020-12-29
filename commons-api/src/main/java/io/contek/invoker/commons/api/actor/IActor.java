package io.contek.invoker.commons.api.actor;

import io.contek.invoker.commons.api.actor.http.IHttpClient;
import io.contek.invoker.commons.api.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;

@ThreadSafe
public interface IActor {

  ICredential getCredential();

  IHttpClient getHttpClient();

  IRateLimitThrottle getRateLimitThrottle();

  Clock getClock();
}
