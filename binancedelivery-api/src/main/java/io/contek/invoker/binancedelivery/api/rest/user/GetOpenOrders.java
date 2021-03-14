package io.contek.invoker.binancedelivery.api.rest.user;

import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._Order;
import io.contek.invoker.binancedelivery.api.rest.user.GetOpenOrders.Response;
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

  private static final ImmutableList<RateLimitQuota> MULTI_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(5));
  private static final ImmutableList<RateLimitQuota> ALL_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(40));

  private String symbol;
  private String pair;

  GetOpenOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOpenOrders setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOpenOrders setPair(@Nullable String pair) {
    this.pair = pair;
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
    return "/dapi/v1/openOrders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    if (pair != null) {
      builder.add("pair", pair);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    if (symbol != null) {
      return ONE_REST_REQUEST;
    }
    if (pair != null) {
      return MULTI_SYMBOLS_REQUIRED_QUOTA;
    }
    return ALL_SYMBOLS_REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
