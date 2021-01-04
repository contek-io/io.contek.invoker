package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public abstract class BaseRestRequest<R> {

  private final IActor actor;

  protected BaseRestRequest(IActor actor) {
    this.actor = actor;
  }

  public final R submit() throws AnyHttpException {
    RestCall call = createCall(actor.getCredential());
    IRateLimitThrottle throttle = actor.getRateLimitThrottle();
    throttle.acquire(getClass().getSimpleName(), getRequiredQuotas());

    RestResponse response = call.submit(actor.getHttpClient());
    return requireNonNull(response.getAs(getResponseType()));
  }

  protected abstract ImmutableList<RateLimitQuota> getRequiredQuotas();

  protected abstract RestCall createCall(ICredential credential);

  protected abstract Class<R> getResponseType();
}
