package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._OrderBook;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.market.GetOrderBook.Response;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderBook extends MarketRestRequest<Response> {

  private String category;
  private String symbol;
  private Integer limit;

  GetOrderBook(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBook setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetOrderBook setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrderBook setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/market/orderbook";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

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
  public static final class Result extends _OrderBook {}
}
