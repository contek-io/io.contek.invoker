package io.contek.invoker.commons.actor.http;

import io.contek.ursa.AcquireTimeoutException;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class HttpBusyException extends AnyHttpException {

  public HttpBusyException(AcquireTimeoutException cause) {
    super(cause);
  }
}
