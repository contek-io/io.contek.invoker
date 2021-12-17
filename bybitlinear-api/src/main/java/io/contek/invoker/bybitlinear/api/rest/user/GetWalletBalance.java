package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.common._WalletBalance;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_READ_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.GetWalletBalance.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetWalletBalance extends UserRestRequest<Response> {

  private String coin;

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
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_POSITION_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Map<String, _WalletBalance>> {}
}
