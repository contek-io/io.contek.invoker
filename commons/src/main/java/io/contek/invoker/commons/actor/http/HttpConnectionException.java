package io.contek.invoker.commons.actor.http;

import java.io.IOException;

public final class HttpConnectionException extends AnyHttpException {

  public HttpConnectionException(IOException cause) {
    super(cause);
  }
}
