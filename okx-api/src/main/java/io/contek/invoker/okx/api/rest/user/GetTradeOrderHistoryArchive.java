package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

public final class GetTradeOrderHistoryArchive
    extends UserRestRequest<GetTradeOrderHistoryArchive.Response> {

  public static final RateLimitRule RATE_LIMIT_RULE =
      RateLimitRule.newBuilder()
          .setName("api_key_rest_get_trade_orders_history_archive")
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
  private String category;
  private Long after;
  private Long before;
  private Integer limit;

  GetTradeOrderHistoryArchive(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeOrderHistoryArchive setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetTradeOrderHistoryArchive setUly(String uly) {
    this.uly = uly;
    return this;
  }

  public GetTradeOrderHistoryArchive setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetTradeOrderHistoryArchive setOrdType(String ordType) {
    this.ordType = ordType;
    return this;
  }

  public GetTradeOrderHistoryArchive setState(String state) {
    this.state = state;
    return this;
  }

  public GetTradeOrderHistoryArchive setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetTradeOrderHistoryArchive setAfter(Long after) {
    this.after = after;
    return this;
  }

  public GetTradeOrderHistoryArchive setBefore(Long before) {
    this.before = before;
    return this;
  }

  public GetTradeOrderHistoryArchive setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/orders-history-archive";
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
    return REQUIRED_QUOTA;
  }

  public static final class Response extends RestResponse<_Order> {}
}
