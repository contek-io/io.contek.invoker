package io.contek.invoker.binancespot.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.common._Order;
import io.contek.invoker.binancespot.api.rest.user.GetOpenOrders.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.binancespot.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetOpenOrders extends UserRestRequest<Response> {

  private static final ImmutableList<TypedPermitRequest> SINGLE_SYMBOL_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(3));
  private static final ImmutableList<TypedPermitRequest> ALL_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(40));

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
    return "/api/v3/openOrders";
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
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return symbol != null ? SINGLE_SYMBOL_REQUIRED_QUOTA : ALL_SYMBOLS_REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
