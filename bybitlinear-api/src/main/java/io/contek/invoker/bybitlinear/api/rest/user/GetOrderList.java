package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.common._Order;
import io.contek.invoker.bybitlinear.api.rest.common.RestPagedResult;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_READ_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.GetOrderList.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderList extends UserRestRequest<Response> {

  private String symbol;
  private String order_status;
  private String order_id;
  private String order_link_id;
  private String order;
  private Integer page;
  private Integer limit;

  GetOrderList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderList setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrderList setOrderStatus(@Nullable String order_status) {
    this.order_status = order_status;
    return this;
  }

  public GetOrderList setOrderId(@Nullable String order_id) {
    this.order_id = order_id;
    return this;
  }

  public GetOrderList setOrderLinkId(@Nullable String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  public GetOrderList setOrder(@Nullable String order) {
    this.order = order;
    return this;
  }

  public GetOrderList setPage(@Nullable Integer page) {
    this.page = page;
    return this;
  }

  public GetOrderList setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/private/linear/order/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (order_status != null) {
      builder.add("order_status", order_status);
    }

    if (order_id != null) {
      builder.add("order_id", order_id);
    }

    if (order_link_id != null) {
      builder.add("order_link_id", order_link_id);
    }

    if (order != null) {
      builder.add("order", order);
    }

    if (page != null) {
      builder.add("page", page);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_ORDER_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<RestPagedResult<_Order>> {}
}
