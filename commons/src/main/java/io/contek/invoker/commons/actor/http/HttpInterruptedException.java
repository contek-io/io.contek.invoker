package io.contek.invoker.commons.actor.http;

import java.io.InterruptedIOException;

public final class HttpInterruptedException extends AnyHttpException {

  public HttpInterruptedException(InterruptedException cause) {
    super(cause);
  }

  public HttpInterruptedException(InterruptedIOException cause) {
    super(cause);
  }
}
