package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._OrderRealtime;
import io.contek.invoker.bybit.api.rest.common.PageListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.GetOrderRealtime.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderRealtime extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private String baseCoin;
  private String settleCoin;
  private String orderId;
  private String orderLinkId;
  private String openOnly;
  private String orderFilter;
  private Integer limit;
  private String cursor;

  GetOrderRealtime(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderRealtime setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetOrderRealtime setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrderRealtime setBaseCoin(@Nullable String baseCoin) {
    this.baseCoin = baseCoin;
    return this;
  }

  public GetOrderRealtime setSettleCoin(@Nullable String settleCoin) {
    this.settleCoin = settleCoin;
    return this;
  }

  public GetOrderRealtime setOrderId(@Nullable String orderId) {
    this.orderId = orderId;
    return this;
  }

  public GetOrderRealtime setOrderLinkId(@Nullable String orderLinkId) {
    this.orderLinkId = orderLinkId;
    return this;
  }

  public GetOrderRealtime setOpenOnly(@Nullable String openOnly) {
    this.openOnly = openOnly;
    return this;
  }

  public GetOrderRealtime setOrderFilter(@Nullable String orderFilter) {
    this.orderFilter = orderFilter;
    return this;
  }

  public GetOrderRealtime setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetOrderRealtime setCursor(@Nullable String cursor) {
    this.cursor = cursor;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/order/realtime";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (baseCoin != null) {
      builder.add("baseCoin", baseCoin);
    }
    if (settleCoin != null) {
      builder.add("settleCoin", settleCoin);
    }
    if (orderId != null) {
      builder.add("orderId", orderId);
    }
    if (orderLinkId != null) {
      builder.add("orderLinkId", orderLinkId);
    }
    if (openOnly != null) {
      builder.add("openOnly", openOnly);
    }
    if (orderFilter != null) {
      builder.add("orderFilter", orderFilter);
    }
    if (limit != null) {
      builder.add("limit", limit);
    }
    if (cursor != null) {
      builder.add("cursor", cursor);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_REALTIME_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends PageListResult<_OrderRealtime> {

    public String category;
  }
}
