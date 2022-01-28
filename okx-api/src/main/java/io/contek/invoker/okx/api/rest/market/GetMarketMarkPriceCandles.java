package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PriceCandlestick;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketMarkPriceCandles
    extends MarketRestRequest<GetMarketMarkPriceCandles.Response> {

  private String instId;
  private Long after;
  private Long before;
  private String bar;
  private Integer limit;

  GetMarketMarkPriceCandles(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketMarkPriceCandles setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetMarketMarkPriceCandles setAfter(Long after) {
    this.after = after;
    return this;
  }

  public GetMarketMarkPriceCandles setBefore(Long before) {
    this.before = before;
    return this;
  }

  public GetMarketMarkPriceCandles setBar(String bar) {
    this.bar = bar;
    return this;
  }

  public GetMarketMarkPriceCandles setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/market/mark-price-candles";
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
  public static final class Response extends RestResponse<_PriceCandlestick> {}
}
