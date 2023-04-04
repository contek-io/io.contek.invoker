package io.contek.invoker.commons.actor.http;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.IOException;

@NotThreadSafe
public final class HttpConnectionException extends AnyHttpException {

  public HttpConnectionException(IOException cause) {
    super(null, cause);
  }
}
