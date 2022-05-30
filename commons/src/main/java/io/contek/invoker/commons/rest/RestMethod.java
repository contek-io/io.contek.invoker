package io.contek.invoker.commons.rest;

import io.vertx.core.http.HttpMethod;

public enum RestMethod {
  GET(HttpMethod.GET),
  POST(HttpMethod.POST),
  PUT(HttpMethod.PUT),
  DELETE(HttpMethod.DELETE);

  private final HttpMethod vertxHttpMethod;

  RestMethod(HttpMethod vertxHttpMethod) {
    this.vertxHttpMethod = vertxHttpMethod;
  }

  public HttpMethod getVertxHttpMethod() {
    return vertxHttpMethod;
  }
}
