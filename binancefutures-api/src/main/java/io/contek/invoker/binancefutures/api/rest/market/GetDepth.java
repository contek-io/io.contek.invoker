package io.contek.invoker.binancefutures.api.rest.market;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import io.contek.invoker.binancefutures.api.common._OrderBookLevel;
import io.contek.invoker.binancefutures.api.rest.market.GetDepth.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetDepth extends MarketRestRequest<Response> {

  public static final ImmutableSortedSet<Integer> SUPPORTED_LIMITS =
      ImmutableSortedSet.of(5, 10, 20, 50, 100, 500, 1000);
  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA_50 =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(2));
  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA_100 =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(5));
  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA_500 =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(10));
  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA_1000 =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(20));

  private String symbol;
  private Integer limit;

  GetDepth(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetDepth setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetDepth setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/depth";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    if (limit != null) {
      checkArgument(SUPPORTED_LIMITS.contains(limit));
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    int limit = this.limit != null ? this.limit : 500;
    if (limit <= 50) {
      return REQUIRED_QUOTA_50;
    }
    if (limit <= 100) {
      return REQUIRED_QUOTA_100;
    }
    if (limit <= 500) {
      return REQUIRED_QUOTA_500;
    }
    if (limit <= 1000) {
      return REQUIRED_QUOTA_1000;
    }
    throw new IllegalArgumentException(Integer.toString(limit));
  }

  @NotThreadSafe
  public static final class Response {

    public Long lastUpdateId;
    public Long E;
    public Long T;

    public List<_OrderBookLevel> bids;
    public List<_OrderBookLevel> asks;
  }
}
