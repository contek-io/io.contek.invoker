package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.rest.common.ResponseWrapper;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetTradeOrdersPending extends UserRestRequest<GetTradeOrdersPending.Response> {

  public static final RateLimitRule RATE_LIMIT_RULE =
      RateLimitRule.newBuilder()
          .setName("api_key_rest_get_trade_orders_pending")
          .setType(API_KEY)
          .setMaxPermits(20)
          .setResetPeriod(Duration.ofSeconds(2))
          .build();

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(RATE_LIMIT_RULE.forPermits(1));

  private String instType;
  private String uly;
  private String instId;
  private String ordType;
  private String state;
  private Long after;
  private Long before;
  private Integer limit;

  GetTradeOrdersPending(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeOrdersPending setInstId(@Nullable String instId) {
    this.instId = instId;
    return this;
  }

  public GetTradeOrdersPending setInstType(@Nullable String instType) {
    this.instType = instType;
    return this;
  }

  public GetTradeOrdersPending setUly(@Nullable String uly) {
    this.uly = uly;
    return this;
  }

  public GetTradeOrdersPending setOrdType(@Nullable String ordType) {
    this.ordType = ordType;
    return this;
  }

  public GetTradeOrdersPending setState(@Nullable String state) {
    this.state = state;
    return this;
  }

  public GetTradeOrdersPending setAfter(@Nullable Long after) {
    this.after = after;
    return this;
  }

  public GetTradeOrdersPending setBefore(@Nullable Long before) {
    this.before = before;
    return this;
  }

  public GetTradeOrdersPending setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/orders-pending";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (instType != null) {
      builder.add("instType", instType);
    }

    if (uly != null) {
      builder.add("uly", uly);
    }

    if (instId != null) {
      builder.add(instId, instId);
    }

    if (ordType != null) {
      builder.add("ordType", ordType);
    }

    if (state != null) {
      builder.add("state", state);
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
    return REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<_Order> {}
}
