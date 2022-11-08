package io.contek.invoker.binanceinverse.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binanceinverse.api.common._Order;
import io.contek.invoker.binanceinverse.api.rest.user.GetOpenOrders.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.binanceinverse.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binanceinverse.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetOpenOrders extends UserRestRequest<Response> {

  private static final ImmutableList<TypedPermitRequest> MULTI_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(5));
  private static final ImmutableList<TypedPermitRequest> ALL_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(40));

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
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
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
