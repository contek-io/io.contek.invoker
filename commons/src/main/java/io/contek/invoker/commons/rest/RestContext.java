package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.IHttpContext;
import io.vertx.ext.web.client.WebClientOptions;

import java.util.Objects;

public record RestContext(String baseUrl, WebClientOptions options) implements IHttpContext {

  public RestContext {
    Objects.requireNonNull(baseUrl);
    Objects.requireNonNull(options);
  }

  public static RestContext of(String baseUrl, WebClientOptions options) {
    return new RestContext(baseUrl, options);
  }

  public static RestContext of(String baseUrl) {
    return of(baseUrl, new WebClientOptions().setUserAgentEnabled(false));
  }

}
