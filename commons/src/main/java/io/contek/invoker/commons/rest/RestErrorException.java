package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.AnyHttpException;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class RestErrorException extends AnyHttpException {

  private final RestResponse response;

  RestErrorException(RestResponse response) {
    super(response.getCode(), response.getStringValue());
    this.response = response;
  }

  public RestResponse getResponse() {
    return response;
  }
}
