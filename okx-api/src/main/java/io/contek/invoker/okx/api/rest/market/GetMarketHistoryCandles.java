package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PriceVolumeCandlestick;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketHistoryCandles
    extends MarketRestRequest<GetMarketHistoryCandles.Response> {

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

  @NotThreadSafe
  public static final class Response extends RestResponse<_PriceVolumeCandlestick> {}
}
