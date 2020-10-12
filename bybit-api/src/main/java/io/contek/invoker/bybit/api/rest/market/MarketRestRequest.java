package io.contek.invoker.bybit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.RestRequest;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_GET_REQUEST;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;

@NotThreadSafe
abstract class MarketRestRequest<T> extends RestRequest<T> {

  MarketRestRequest(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_GET_REQUEST;
  }
}
