package io.contek.invoker.bybit.api.rest.market;

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

  public GetKline getKlineList() {
    return new GetKline(actor, context);
  }

  public GetOrderBook getOrderBook() {
    return new GetOrderBook(actor, context);
  }

  public GetInstrumentsInfo getInstrumentsInfo() {
    return new GetInstrumentsInfo(actor, context);
  }

  public GetTickers getTickers() {
    return new GetTickers(actor, context);
  }
}
