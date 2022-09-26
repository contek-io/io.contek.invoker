package io.contek.invoker.commons.actor.http;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.InterruptedIOException;

@NotThreadSafe
public final class HttpInterruptedException extends AnyHttpException {

  public HttpInterruptedException(InterruptedException cause) {
    super(null, cause);
  }

  public HttpInterruptedException(InterruptedIOException cause) {
    super(null, cause);
  }
}
