package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.rest.common.RestPagedResult;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_READ_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetOrderList.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderList extends UserRestRequest<Response> {

  private String symbol;

  GetOrderList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderList setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/open-api/order/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_PRIVATE_ORDER_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<RestPagedResult<_Order>> {}
}
