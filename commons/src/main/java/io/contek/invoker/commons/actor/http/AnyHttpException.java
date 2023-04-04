package io.contek.invoker.commons.actor.http;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AnyHttpException extends RuntimeException {

  private final Integer code;

  public AnyHttpException(@Nullable Integer code, @Nullable String message) {
    super(message);
    this.code = code;
  }

  public AnyHttpException(@Nullable Integer code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  public AnyHttpException(@Nullable Integer code, @Nullable String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public boolean hasResponse() {
    return code != null;
  }

  public boolean isClientError() {
    return code != null && code >= 400 && code < 500;
  }

  public boolean isServerError() {
    return code != null && code >= 500 && code < 600;
  }

  @Nullable
  public Integer getCode() {
    return code;
  }
}
