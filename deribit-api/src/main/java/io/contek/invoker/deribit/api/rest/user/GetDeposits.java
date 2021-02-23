package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Deposit;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

public final class GetDeposits extends UserRestRequest<GetDeposits.Response> {
  private String currency;

  GetDeposits(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetDeposits setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/get_deposits";
  }


  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(currency);
    builder.add("currency", currency);

    return builder.build();
  }

  public static final class Result {
    public int count;
    public List<_Deposit> data;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Result> {
  }
}
