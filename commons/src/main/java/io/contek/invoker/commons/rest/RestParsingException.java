package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.AnyHttpException;

public final class RestParsingException extends AnyHttpException {

  private final RestResponse response;
  private final Class<?> type;

  public RestParsingException(RestResponse response, Class<?> type, Throwable t) {
    super(response.getStringValue(), t);
    this.response = response;
    this.type = type;
  }

  public RestResponse getResponse() {
    return response;
  }

  public Class<?> getType() {
    return type;
  }
}
