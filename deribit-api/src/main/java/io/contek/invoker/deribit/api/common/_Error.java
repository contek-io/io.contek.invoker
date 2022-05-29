package io.contek.invoker.deribit.api.common;

public final class _Error {

  public int code;
  public String message;

  @Override
  public String toString() {
    return "_Error{" +
            "code=" + code +
            ", message='" + message + '\'' +
            '}';
  }
}
