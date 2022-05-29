package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

public final class MarketRestApi {

  private final IActor actor;
  private final RestContext context;

  public MarketRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetMarkets getMarkets() {
    return new GetMarkets(actor, context);
  }

  public GetSingleMarket getSingleMarket() {
    return new GetSingleMarket(actor, context);
  }

  public GetFutures getFutures() {
    return new GetFutures(actor, context);
  }

  public GetMarketsCandles getMarketsCandles() {
    return new GetMarketsCandles(actor, context);
  }

  public GetOrderBook getOrderBook() {
    return new GetOrderBook(actor, context);
  }

  public GetFutureStats getFutureStats() {
    return new GetFutureStats(actor, context);
  }
}
