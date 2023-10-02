package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._OrderHistory;
import io.contek.invoker.bybit.api.rest.common.PageListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.GetOrderHistory.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderHistory extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private String baseCoin;
  private String settleCoin;
  private String orderId;
  private String orderLinkId;
  private String orderFilter;
  private String orderStatus;
  private String startTime;
  private String endTime;
  private Integer limit;
  private String cursor;

  GetOrderHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderHistory setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetOrderHistory setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrderHistory setBaseCoin(@Nullable String baseCoin) {
    this.baseCoin = baseCoin;
    return this;
  }

  public GetOrderHistory setSettleCoin(@Nullable String settleCoin) {
    this.settleCoin = settleCoin;
    return this;
  }

  public GetOrderHistory setOrderId(@Nullable String orderId) {
    this.orderId = orderId;
    return this;
  }

  public GetOrderHistory setOrderLinkId(@Nullable String orderLinkId) {
    this.orderLinkId = orderLinkId;
    return this;
  }

  public GetOrderHistory setOrderFilter(@Nullable String orderFilter) {
    this.orderFilter = orderFilter;
    return this;
  }

  public GetOrderHistory setOrderStatus(@Nullable String orderStatus) {
    this.orderStatus = orderStatus;
    return this;
  }

  public GetOrderHistory setStartTime(@Nullable String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetOrderHistory setEndTime(@Nullable String endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetOrderHistory setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetOrderHistory setCursor(@Nullable String cursor) {
    this.cursor = cursor;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/order/history";
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
    if (orderFilter != null) {
      builder.add("orderFilter", orderFilter);
    }
    if (orderStatus != null) {
      builder.add("orderStatus", orderStatus);
    }
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
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
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_HISTORY_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends PageListResult<_OrderHistory> {

    public String category;
  }
}
