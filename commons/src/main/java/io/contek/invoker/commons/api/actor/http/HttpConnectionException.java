package io.contek.invoker.commons.api.actor.http;

import javax.annotation.concurrent.Immutable;
import java.io.IOException;

@Immutable
public final class HttpConnectionException extends AnyHttpException {

  public HttpConnectionException(IOException cause) {
    super(cause);
  }
}
