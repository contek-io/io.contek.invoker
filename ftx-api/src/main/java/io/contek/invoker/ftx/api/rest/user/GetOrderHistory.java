package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.PaginationRestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetOrderHistory extends UserRestRequest<GetOrderHistory.Response> {

  private String market;
  private String side;
  private String orderType;
  private Long startTime;
  private Long endTime;

  GetOrderHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderHistory setMarket(String market) {
    this.market = market;
    return this;
  }

  public GetOrderHistory setSide(String side) {
    this.side = side;
    return this;
  }

  public GetOrderHistory setOrderType(String orderType) {
    this.orderType = orderType;
    return this;
  }

  public GetOrderHistory setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetOrderHistory setEndTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/orders/history";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }

    if (side != null) {
      builder.add("side", side);
    }

    if (orderType != null) {
      builder.add("orderType", orderType);
    }

    if (startTime != null) {
      builder.add("start_time", startTime);
    }

    if (endTime != null) {
      builder.add("end_time", endTime);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends PaginationRestResponse<List<_Order>> {}
}
