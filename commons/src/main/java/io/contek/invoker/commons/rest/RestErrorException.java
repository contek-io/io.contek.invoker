package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.AnyHttpException;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class RestErrorException extends AnyHttpException {

  private final RestResponse response;

  RestErrorException(RestResponse response) {
    super(response.getStringValue());
    this.response = response;
  }

  public RestResponse getResponse() {
    return response;
  }

  public boolean isClientError() {
    int status = response.getCode();
    return status >= 400 && status < 500;
  }

  public boolean isServerError() {
    int status = response.getCode();
    return status >= 500 && status < 600;
  }
}
