package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Market;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketsCandles extends MarketRestRequest<GetMarketsCandles.Response> {

  private String market_name;
  private Integer resolution;
  private Integer limit;
  private Long start_time;
  private Long end_time;

  GetMarketsCandles(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketsCandles setMarketName(String market_name) {
    this.market_name = market_name;
    return this;
  }

  public GetMarketsCandles setResolution(Integer resolution) {
    this.resolution = resolution;
    return this;
  }

  public GetMarketsCandles setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetMarketsCandles setStartTime(Long start_time) {
    this.start_time = start_time;
    return this;
  }

  public GetMarketsCandles setEndTime(Long end_time) {
    this.end_time = end_time;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(market_name);
    return format("/api/markets/{0}/candles", market_name);
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(resolution);
    builder.add("resolution", resolution);

    if (limit != null) {
      builder.add("limit", limit);
    }

    if (start_time != null) {
      builder.add("start_time", start_time);
    }

    if (end_time != null) {
      builder.add("end_time", end_time);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Market>> {}
}
