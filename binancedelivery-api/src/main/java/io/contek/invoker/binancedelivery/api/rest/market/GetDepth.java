package io.contek.invoker.binancedelivery.api.rest.market;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import io.contek.invoker.binancedelivery.api.common._OrderBookLevel;
import io.contek.invoker.binancedelivery.api.rest.market.GetDepth.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;

@NotThreadSafe
public final class GetDepth extends MarketRestRequest<Response> {

  public static final ImmutableSortedSet<Integer> SUPPORTED_LIMITS =
      ImmutableSortedSet.of(5, 10, 20, 50, 100, 500, 1000);

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
    return "/dapi/v1/depth";
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
    int requestPermits = limit == null || limit <= 100 ? 1 : limit / 100;
    return ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(requestPermits));
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
