package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PriceVolumeCandlestick;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketCandles extends MarketRestRequest<GetMarketCandles.Response> {

  private String instId;
  private Long after;
  private Long before;
  private String bar;
  private Integer limit;

  GetMarketCandles(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketCandles setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetMarketCandles setAfter(Long after) {
    this.after = after;
    return this;
  }

  public GetMarketCandles setBefore(Long before) {
    this.before = before;
    return this;
  }

  public GetMarketCandles setBar(String bar) {
    this.bar = bar;
    return this;
  }

  public GetMarketCandles setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/market/candles";
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
