package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
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
