package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._Ticker;
import io.contek.invoker.bybit.api.rest.common.ListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.market.GetTickers.Response;

@NotThreadSafe
public final class GetTickers extends MarketRestRequest<Response> {

  private String category;
  private String symbol;
  private String baseCoin;
  private String expDate;

  GetTickers(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTickers setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetTickers setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetTickers setBaseCoin(@Nullable String baseCoin) {
    this.baseCoin = baseCoin;
    return this;
  }

  public GetTickers setExpDate(@Nullable String expDate) {
    this.expDate = expDate;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/market/tickers";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (category != null) {
      builder.add("category", category);
    }

    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (baseCoin != null) {
      builder.add("baseCoin", baseCoin);
    }
    if (expDate != null) {
      builder.add("expDate", expDate);
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
  public static final class Result extends ListResult<_Ticker> {

    public String category;
  }
}
