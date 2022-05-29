package io.contek.invoker.commons.actor.http;

public abstract class AnyHttpException extends RuntimeException {

  public AnyHttpException(String message) {
    super(message);
  }

  public AnyHttpException(Throwable cause) {
    super(cause);
  }

  public AnyHttpException(String message, Throwable cause) {
    super(message, cause);
  }
}
