package io.contek.invoker.deribit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._ChartData;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_IP_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetChartData extends MarketRestRequest<GetChartData.Response> {

  private String instrument_name;
  private Long start_timestamp;
  private Long end_timestamp;
  private String resolution;

  GetChartData(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetChartData setInstrumentName(String instrument_name) {
    this.instrument_name = instrument_name;
    return this;
  }

  public GetChartData setStartTimestamp(Long start_timestamp) {
    this.start_timestamp = start_timestamp;
    return this;
  }

  public GetChartData setEndTimestamp(Long end_timestamp) {
    this.end_timestamp = end_timestamp;
    return this;
  }

  public GetChartData setResolution(String resolution) {
    this.resolution = resolution;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/public/get_tradingview_chart_data";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);
    requireNonNull(start_timestamp);
    builder.add("start_timestamp", start_timestamp);
    requireNonNull(end_timestamp);
    builder.add("end_timestamp", end_timestamp);
    requireNonNull(resolution);
    builder.add("resolution", resolution);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_NON_MATCHING_ENGINE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_ChartData> {}
}
