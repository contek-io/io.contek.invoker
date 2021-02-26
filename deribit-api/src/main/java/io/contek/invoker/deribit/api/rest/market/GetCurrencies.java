package io.contek.invoker.deribit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Currency;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_IP_NON_MATCHING_ENGINE_REQUEST;

@NotThreadSafe
public final class GetCurrencies extends MarketRestRequest<GetCurrencies.Response> {

  GetCurrencies(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/public/get_currencies";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_NON_MATCHING_ENGINE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Currency>> {}
}
