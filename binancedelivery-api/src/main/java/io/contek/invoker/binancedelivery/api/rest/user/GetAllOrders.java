package io.contek.invoker.binancedelivery.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._Order;
import io.contek.invoker.binancedelivery.api.rest.user.GetAllOrders.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetAllOrders extends UserRestRequest<Response> {

  public static final int MAX_LIMIT = 100;
  private static final ImmutableList<TypedPermitRequest> SYMBOL_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(20));
  private static final ImmutableList<TypedPermitRequest> PAIR_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(40));

  private String symbol;
  private String pair;
  private Long orderId;
  private Long startTime;
  private Long endTime;
  private Integer limit;

  GetAllOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetAllOrders setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetAllOrders setPair(@Nullable String pair) {
    this.pair = pair;
    return this;
  }

  public GetAllOrders setOrderId(@Nullable Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public GetAllOrders setStartTime(@Nullable Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetAllOrders setEndTime(@Nullable Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetAllOrders setLimit(@Nullable Integer limit) {
    this.limit = limit;
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
    return "/fapi/v1/allOrders";
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

    if (orderId != null) {
      builder.add("orderId", orderId);
    }

    if (startTime != null) {
      builder.add("startTime", startTime);
    }

    if (endTime != null) {
      builder.add("endTime", endTime);
    }

    if (limit != null) {
      checkArgument(limit <= MAX_LIMIT);
      builder.add("limit", limit);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    if (symbol != null) {
      return SYMBOL_REQUIRED_QUOTA;
    }
    if (pair != null) {
      return PAIR_REQUIRED_QUOTA;
    }
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
