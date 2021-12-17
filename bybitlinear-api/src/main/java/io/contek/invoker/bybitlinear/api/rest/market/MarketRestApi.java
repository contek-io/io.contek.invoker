package io.contek.invoker.bybitlinear.api.rest.market;

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

  public GetAccountRatio getAccountRatio() {
    return new GetAccountRatio(actor, context);
  }

  public GetKlineList getKlineList() {
    return new GetKlineList(actor, context);
  }

  public GetOrderBookL2 getOrderBookL2() {
    return new GetOrderBookL2(actor, context);
  }

  public GetSymbols getSymbols() {
    return new GetSymbols(actor, context);
  }

  public GetTickers getTickers() {
    return new GetTickers(actor, context);
  }
}
