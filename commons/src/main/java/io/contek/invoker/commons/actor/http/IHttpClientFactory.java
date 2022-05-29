package io.contek.invoker.commons.actor.http;

public interface IHttpClientFactory {

  IHttpClient create(IHttpContext context);
}
