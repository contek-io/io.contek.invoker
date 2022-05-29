package io.contek.invoker.commons.actor.ratelimit;

import java.net.InetAddress;

public interface IRateLimitThrottleFactory {

  IRateLimitThrottle create(InetAddress boundLocalAddress, String apiKeyId);
}
