package io.contek.invoker.bybit.api.rest.user;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_GET_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetWalletBalance.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._WalletBalance;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetWalletBalance extends UserRestRequest<Response> {

  public String coin;

  GetWalletBalance(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetWalletBalance setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/wallet/balance";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (coin != null) {
      builder.add("coin", coin);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_GET_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_WalletBalance> {}
}
