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

  public GetBookSummaryByCurrency getBookSummaryByCurrency() {
    return new GetBookSummaryByCurrency(actor, context);
  }

  public GetBookSummaryByInstrument getBookSummaryByInstrument() {
    return new GetBookSummaryByInstrument(actor, context);
  }

  public GetCurrencies getCurrencies() {
    return new GetCurrencies(actor, context);
  }

  public GetInstruments getInstruments() {
    return new GetInstruments(actor, context);
  }

  public GetOrderBook getOrderBook() {
    return new GetOrderBook(actor, context);
  }

  public GetTicker getTicker() {
    return new GetTicker(actor, context);
  }
}
