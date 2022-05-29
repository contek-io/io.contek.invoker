package io.contek.invoker.binancespot.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.common._Order;
import io.contek.invoker.binancespot.api.rest.user.GetAllOrders.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancespot.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

public final class GetAllOrders extends UserRestRequest<Response> {

  public static final int MAX_LIMIT = 1000;
  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(10));

  private String symbol;
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

  public GetAllOrders setOrderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public GetAllOrders setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetAllOrders setEndTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetAllOrders setLimit(Integer limit) {
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
    return "/api/v3/allOrders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

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
    return REQUIRED_QUOTA;
  }

  public static final class Response extends ArrayList<_Order> {}
}
