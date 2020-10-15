package io.contek.invoker.bybit.api.rest.market;

import static io.contek.invoker.bybit.api.rest.market.GetKlineList.Response;
import static java.util.Objects.requireNonNull;

import io.contek.invoker.bybit.api.common._Kline;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetKlineList extends MarketRestRequest<Response> {

  public static final int MAX_LIMIT = 200;

  private String symbol;
  private String interval;
  private Long from;
  private Integer limit;

  GetKlineList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetKlineList setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetKlineList setInterval(String interval) {
    this.interval = interval;
    return this;
  }

  public GetKlineList setFrom(Long from) {
    this.from = from;
    return this;
  }

  public GetKlineList setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/public/kline/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(interval);
    builder.add("interval", interval);

    requireNonNull(from);
    builder.add("from", from);

    if (limit != null) {
      if (limit > MAX_LIMIT) {
        throw new IllegalArgumentException(Integer.toString(limit));
      }
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Kline>> {}
}
