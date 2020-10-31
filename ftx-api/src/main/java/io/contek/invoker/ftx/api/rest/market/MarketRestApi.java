package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
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

  public GetMarketsCandles getMarketsCandles() {
    return new GetMarketsCandles(actor, context);
  }

  public GetOrderBook getOrderBook() {
    return new GetOrderBook(actor, context);
  }
}
