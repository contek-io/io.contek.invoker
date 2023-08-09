package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._Kline;
import io.contek.invoker.bybit.api.rest.common.ListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.market.GetKline.Response;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetKline extends MarketRestRequest<Response> {

  public static final int MAX_LIMIT = 200;

  private String category;
  private String symbol;
  private String interval;
  private Long start;
  private Long end;
  private Integer limit;

  GetKline(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetKline setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetKline setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetKline setInterval(String interval) {
    this.interval = interval;
    return this;
  }

  public GetKline setStart(@Nullable Long start) {
    this.start = start;
    return this;
  }

  public GetKline setEnd(@Nullable Long end) {
    this.end = end;
    return this;
  }

  public GetKline setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/market/kline";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(interval);
    builder.add("interval", interval);

    if (start != null) {
      builder.add("start", start);
    }
    if (end != null) {
      builder.add("end", end);
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
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends ListResult<_Kline> {

    public String symbol;
    public String category;
  }
}
