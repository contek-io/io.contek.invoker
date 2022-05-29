package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpContext;
import io.contek.invoker.security.ApiKey;

public interface IActorFactory {

  IActor create(ApiKey apiKey, IHttpContext context);
}
