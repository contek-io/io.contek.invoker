package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._StopOrder;
import io.contek.invoker.bybit.api.rest.common.RestPagedResult;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_READ_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetStopOrderList.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetStopOrderList extends UserRestRequest<Response> {

  private String symbol;
  private String stop_order_status;
  private String direction;
  private Integer limit;
  private String cursor;

  GetStopOrderList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetStopOrderList setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetStopOrderList setStopOrderStatus(@Nullable String stop_order_status) {
    this.stop_order_status = stop_order_status;
    return this;
  }

  public GetStopOrderList setDirection(@Nullable String direction) {
    this.direction = direction;
    return this;
  }

  public GetStopOrderList setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetStopOrderList setCursor(@Nullable String cursor) {
    this.cursor = cursor;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/stop-order/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (stop_order_status != null) {
      builder.add("stop_order_status", stop_order_status);
    }

    if (direction != null) {
      builder.add("direction", direction);
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
    return ONE_REST_PRIVATE_ORDER_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<RestPagedResult<_StopOrder>> {}
}
