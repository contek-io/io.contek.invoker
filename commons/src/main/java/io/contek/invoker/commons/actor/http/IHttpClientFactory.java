package io.contek.invoker.commons.actor.http;

import io.vertx.core.http.HttpClient;

public interface IHttpClientFactory {

  HttpClient create(IHttpContext context);
}
