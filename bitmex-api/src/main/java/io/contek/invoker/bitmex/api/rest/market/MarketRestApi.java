package io.contek.invoker.bitmex.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

public final class MarketRestApi {

  private final IActor actor;
  private final RestContext context;

  public MarketRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetInstrumentActive getInstrumentActive() {
    return new GetInstrumentActive(actor, context);
  }

  public GetInstrumentIndices getInstrumentIndices() {
    return new GetInstrumentIndices(actor, context);
  }

  public GetOrderBookL2 getOrderBookL2() {
    return new GetOrderBookL2(actor, context);
  }

  public GetStats getStats() {
    return new GetStats(actor, context);
  }

  public GetTradeBucketed getTradeBucketed() {
    return new GetTradeBucketed(actor, context);
  }
}
