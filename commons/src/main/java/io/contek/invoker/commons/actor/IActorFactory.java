package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpContext;
import io.contek.invoker.security.ApiKey;
import io.vertx.core.Vertx;

public interface IActorFactory {

  IActor create(ApiKey apiKey, Vertx vertx, IHttpContext context);
}
