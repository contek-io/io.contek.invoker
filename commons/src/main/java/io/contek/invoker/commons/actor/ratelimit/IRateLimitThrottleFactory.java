package io.contek.invoker.commons.actor.ratelimit;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.net.InetAddress;

@ThreadSafe
public interface IRateLimitThrottleFactory {

  IRateLimitThrottle create(InetAddress boundLocalAddress, @Nullable String apiKeyId);
}
