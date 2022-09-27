package io.contek.invoker.bybit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.RestRequest;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PUBLIC_GET_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
abstract class MarketRestRequest<T extends ResponseWrapper<?>> extends RestRequest<T> {

  MarketRestRequest(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PUBLIC_GET_REQUEST;
  }
}
