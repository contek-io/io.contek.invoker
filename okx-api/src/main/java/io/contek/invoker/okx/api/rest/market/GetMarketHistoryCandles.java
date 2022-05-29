package io.contek.invoker.okx.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PriceVolumeCandlestick;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.IP;
import static java.util.Objects.requireNonNull;

public final class GetMarketHistoryCandles
    extends MarketRestRequest<GetMarketHistoryCandles.Response> {

  public static final RateLimitRule RATE_LIMIT_RULE =
      RateLimitRule.newBuilder()
          .setName("ip_rest_get_market_history_candles")
          .setType(IP)
          .setMaxPermits(20)
          .setResetPeriod(Duration.ofSeconds(2))
          .build();

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(RATE_LIMIT_RULE.forPermits(1));

  private String instId;
  private Long after;
  private Long before;
  private String bar;
  private Integer limit;

  GetMarketHistoryCandles(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketHistoryCandles setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetMarketHistoryCandles setAfter(Long after) {
    this.after = after;
    return this;
  }

  public GetMarketHistoryCandles setBefore(Long before) {
    this.before = before;
    return this;
  }

  public GetMarketHistoryCandles setBar(String bar) {
    this.bar = bar;
    return this;
  }

  public GetMarketHistoryCandles setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/market/history-candles";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instId);
    builder.add("instId", instId);

    if (before != null) {
      builder.add("before", before);
    }

    if (after != null) {
      builder.add("after", after);
    }

    if (bar != null) {
      builder.add("bar", bar);
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

  public static final class Response extends RestResponse<_PriceVolumeCandlestick> {}
}
