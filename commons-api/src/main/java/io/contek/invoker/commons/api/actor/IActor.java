package io.contek.invoker.commons.api.actor;

import io.contek.invoker.commons.api.actor.http.IHttpClient;
import io.contek.invoker.commons.api.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.api.actor.security.ICredential;
import java.time.Clock;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IActor {

  ICredential getCredential();

  IHttpClient getHttpClient();

  IRateLimitThrottle getRateLimitThrottle();

  Clock getClock();
}
