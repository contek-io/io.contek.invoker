package io.contek.invoker.binancelinear.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancelinear.api.common._LongShortRatio;
import io.contek.invoker.binancelinear.api.rest.market.GetTopLongShortAccountRatio.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancelinear.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetTopLongShortAccountRatio extends MarketRestRequest<Response> {

  private String symbol;
  private String period;
  private Long startTime;
  private Long endTime;
  private Integer limit;

  GetTopLongShortAccountRatio(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTopLongShortAccountRatio setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetTopLongShortAccountRatio setPeriod(String period) {
    this.period = period;
    return this;
  }

  public GetTopLongShortAccountRatio setStartTime(@Nullable Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetTopLongShortAccountRatio setEndTime(@Nullable Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetTopLongShortAccountRatio setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/futures/data/topLongShortAccountRatio";
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
    checkNotNull(period);
    builder.add("period", period);
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }
    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_LongShortRatio> {}
}
