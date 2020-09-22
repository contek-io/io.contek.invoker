package io.contek.invoker.binancefutures.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._Candlestick;
import io.contek.invoker.binancefutures.api.rest.market.GetKlines.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

/**
 * Default {@code limit} is 500. Time filters are inclusive.
 *
 * <ul>
 *   <li>When neither {@code startTime} nor {@code endTime} is set, returns most recent data.
 *   <li>When only {@code endTime} is set, returns ASC order data up to the ending time.
 *   <li>When only {@code startTime} is set, returns ASC order data from the starting time.
 *   <li>When both {@code startTime} and {@code endTime} are set, returns ASC order data from the
 *       starting time.
 * </ul>
 */
@NotThreadSafe
public final class GetKlines extends MarketRestRequest<Response> {

  public static final int MAX_LIMIT = 1000;

  private String symbol;
  private String interval;
  private Long startTime;
  private Long endTime;
  private Integer limit;

  GetKlines(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetKlines setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetKlines setInterval(String interval) {
    this.interval = interval;
    return this;
  }

  public GetKlines setStartTime(@Nullable Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetKlines setEndTime(@Nullable Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetKlines setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/klines";
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);
    checkNotNull(interval);
    builder.add("interval", interval);
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }
    if (limit != null) {
      checkArgument(limit <= MAX_LIMIT);
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Candlestick> {}
}
