package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Fill;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetFills extends UserRestRequest<GetFills.Response> {

  private String market;
  private Long start_time;
  private Long end_time;
  private String order;
  private String orderId;

  GetFills(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetFills setMarket(@Nullable String market) {
    this.market = market;
    return this;
  }

  public GetFills setStartTime(@Nullable Long start_time) {
    this.start_time = start_time;
    return this;
  }

  public GetFills setEndTime(@Nullable Long end_time) {
    this.end_time = end_time;
    return this;
  }

  public GetFills setOrder(@Nullable String order) {
    this.order = order;
    return this;
  }

  public GetFills setOrderId(@Nullable String orderId) {
    this.orderId = orderId;
    return this;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected Class<GetFills.Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/fills";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }
    if (start_time != null) {
      builder.add("start_time", start_time);
    }
    if (end_time != null) {
      builder.add("end_time", end_time);
    }
    if (order != null) {
      builder.add("order", order);
    }
    if (orderId != null) {
      builder.add("orderId", orderId);
    }
    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Fill>> {}
}
