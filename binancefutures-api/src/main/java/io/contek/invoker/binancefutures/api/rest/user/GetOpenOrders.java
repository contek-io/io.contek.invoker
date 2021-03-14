package io.contek.invoker.binancefutures.api.rest.user;

import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._Order;
import io.contek.invoker.binancefutures.api.rest.user.GetOpenOrders.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetOpenOrders extends UserRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> ALL_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(40));

  private String symbol;

  GetOpenOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOpenOrders setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/openOrders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return symbol != null ? ONE_REST_REQUEST : ALL_SYMBOLS_REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
