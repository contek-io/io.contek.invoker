package io.contek.invoker.ursa.core.api;

import static java.lang.String.format;

public final class NegativePermitException extends UrsaException {

  NegativePermitException(int requestPermits) {
    super(format("Requested permits is negative: %d", requestPermits));
  }
}
