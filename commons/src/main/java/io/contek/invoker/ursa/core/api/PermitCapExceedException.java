package io.contek.invoker.ursa.core.api;

import static java.lang.String.format;

public final class PermitCapExceedException extends UrsaException {

  PermitCapExceedException(int requestPermits, int permitCap) {
    super(format("Requested permits exceeds cap: %d > %d", requestPermits, permitCap));
  }
}
