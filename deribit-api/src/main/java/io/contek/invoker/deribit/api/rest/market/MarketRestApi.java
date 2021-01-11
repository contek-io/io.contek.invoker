package io.contek.invoker.deribit.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

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
