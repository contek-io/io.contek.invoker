package io.contek.invoker.commons.actor.http;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class HttpInterruptedException extends AnyHttpException {

  public HttpInterruptedException(InterruptedException cause) {
    super(cause);
  }
}
