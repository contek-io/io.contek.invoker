package io.contek.invoker.commons.actor.http;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class AnyHttpException extends RuntimeException {

  public AnyHttpException(@Nullable String message) {
    super(message);
  }

  public AnyHttpException(Throwable cause) {
    super(cause);
  }
}
