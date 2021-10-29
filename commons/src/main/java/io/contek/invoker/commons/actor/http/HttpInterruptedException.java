package io.contek.invoker.commons.actor.http;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.InterruptedIOException;

@NotThreadSafe
public final class HttpInterruptedException extends AnyHttpException {

  public HttpInterruptedException(InterruptedException cause) {
    super(cause);
  }

  public HttpInterruptedException(InterruptedIOException cause) {
    super(cause);
  }
}
