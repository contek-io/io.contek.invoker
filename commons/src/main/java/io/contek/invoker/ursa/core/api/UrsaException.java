package io.contek.invoker.ursa.core.api;

public abstract class UrsaException extends RuntimeException {

  protected UrsaException(String message) {
    super(message);
  }

  protected UrsaException(Throwable cause) {
    super(cause);
  }
}
