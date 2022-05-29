package io.contek.invoker.binancedelivery.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._LongShortRatio;
import io.contek.invoker.binancedelivery.api.rest.market.GetGlobalLongShortAccountRatio.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

public final class GetGlobalLongShortAccountRatio extends MarketRestRequest<Response> {

  private String symbol;
  private String period;
  private Long startTime;
  private Long endTime;
  private Integer limit;

  GetGlobalLongShortAccountRatio(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetGlobalLongShortAccountRatio setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetGlobalLongShortAccountRatio setPeriod(String period) {
    this.period = period;
    return this;
  }

  public GetGlobalLongShortAccountRatio setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetGlobalLongShortAccountRatio setEndTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetGlobalLongShortAccountRatio setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/futures/data/globalLongShortAccountRatio";
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

  public static final class Response extends ArrayList<_LongShortRatio> {}
}
