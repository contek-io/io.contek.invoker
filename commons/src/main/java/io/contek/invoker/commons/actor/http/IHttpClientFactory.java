package io.contek.invoker.commons.actor.http;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IHttpClientFactory {

  IHttpClient create(IHttpContext context);
}
