package io.contek.ursa.cache;

import io.contek.invoker.ursa.core.api.UrsaException;

public class NoSuchRateLimitException extends UrsaException {
  NoSuchRateLimitException(String name) {
    super(String.format("Could not find rate limit %s", name));
  }
}
