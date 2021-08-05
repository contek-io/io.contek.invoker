package io.contek.invoker.ftx.api.rest;

import io.contek.invoker.commons.rest.RestResponse;

public final class RestErrorException extends io.contek.invoker.commons.rest.RestErrorException {

  private final RestErrorExceptionResponse error;

  public RestErrorException(RestResponse response) {
    super(response);
    this.error = response.getAs(RestErrorExceptionResponse.class);
  }

  public RestErrorExceptionResponse getError() {
    return error;
  }

  public static class RestErrorExceptionResponse {
    public Boolean success;
    public String error;
  }
}
