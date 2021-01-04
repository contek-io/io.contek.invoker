package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpContext;
import io.contek.invoker.security.ApiKey;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IActorFactory {

  IActor create(@Nullable ApiKey apiKey, IHttpContext context);
}
