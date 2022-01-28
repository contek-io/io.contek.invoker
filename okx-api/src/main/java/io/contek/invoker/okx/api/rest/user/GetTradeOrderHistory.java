package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetTradeOrderHistory extends UserRestRequest<GetTradeOrderHistory.Response> {

  private String instType;
  private String uly;
  private String instId;
  private String ordType;
  private String state;
  private String category;
  private Long after;
  private Long before;
  private Integer limit;

  GetTradeOrderHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeOrderHistory setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetTradeOrderHistory setUly(@Nullable String uly) {
    this.uly = uly;
    return this;
  }

  public GetTradeOrderHistory setInstId(@Nullable String instId) {
    this.instId = instId;
    return this;
  }

  public GetTradeOrderHistory setOrdType(@Nullable String ordType) {
    this.ordType = ordType;
    return this;
  }

  public GetTradeOrderHistory setState(@Nullable String state) {
    this.state = state;
    return this;
  }

  public GetTradeOrderHistory setCategory(@Nullable String category) {
    this.category = category;
    return this;
  }

  public GetTradeOrderHistory setAfter(@Nullable Long after) {
    this.after = after;
    return this;
  }

  public GetTradeOrderHistory setBefore(@Nullable Long before) {
    this.before = before;
    return this;
  }

  public GetTradeOrderHistory setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/orders-history";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instType);
    builder.add("instType", instType);

    if (uly != null) {
      builder.add("uly", uly);
    }

    if (instId != null) {
      builder.add("instId", instId);
    }

    if (ordType != null) {
      builder.add("ordType", ordType);
    }

    if (state != null) {
      builder.add("state", state);
    }

    if (category != null) {
      builder.add("category", category);
    }

    if (after != null) {
      builder.add("after", after);
    }

    if (before != null) {
      builder.add("before", before);
    }

    if (limit != null) {
      builder.add("limit", limit);
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
  public static final class Response extends RestResponse<_Order> {}
}
