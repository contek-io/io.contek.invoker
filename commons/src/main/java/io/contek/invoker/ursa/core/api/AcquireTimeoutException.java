package io.contek.invoker.ursa.core.api;

import java.time.Duration;

import static java.lang.String.format;

public final class AcquireTimeoutException extends UrsaException {

  AcquireTimeoutException(Duration duration) {
    super(format("Acquire permit timeout %s exceeded", duration));
  }
}
