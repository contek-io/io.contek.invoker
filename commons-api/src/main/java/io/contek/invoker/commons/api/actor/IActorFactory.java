package io.contek.invoker.commons.api.actor;

import io.contek.invoker.commons.api.actor.http.IHttpContext;
import io.contek.invoker.commons.api.actor.security.ApiKey;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IActorFactory {

  IActor create(@Nullable ApiKey apiKey, IHttpContext context);
}
