package io.contek.invoker.commons.actor.http;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AnyHttpException extends RuntimeException {

  public AnyHttpException(@Nullable String message) {
    super(message);
  }

  public AnyHttpException(Throwable cause) {
    super(cause);
  }

  public AnyHttpException(@Nullable String message, Throwable cause) {
    super(message, cause);
  }
}
