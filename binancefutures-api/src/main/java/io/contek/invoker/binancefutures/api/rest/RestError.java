package io.contek.invoker.binancefutures.api.rest;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RestError {

  private final Integer code;
  private final String msg;

  public RestError(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
