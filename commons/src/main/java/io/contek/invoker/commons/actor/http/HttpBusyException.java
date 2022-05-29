package io.contek.invoker.commons.actor.http;

import io.contek.invoker.ursa.core.api.AcquireTimeoutException;

public final class HttpBusyException extends AnyHttpException {

  public HttpBusyException(AcquireTimeoutException cause) {
    super(cause);
  }
}
