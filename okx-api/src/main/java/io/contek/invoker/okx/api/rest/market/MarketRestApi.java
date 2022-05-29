package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

public final class MarketRestApi {

  private final IActor actor;
  private final RestContext context;

  public MarketRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetMarketBooks getMarketBooks() {
    return new GetMarketBooks(actor, context);
  }

  public GetMarketCandles getMarketCandles() {
    return new GetMarketCandles(actor, context);
  }

  public GetMarketHistoryCandles getMarketHistoryCandles() {
    return new GetMarketHistoryCandles(actor, context);
  }

  public GetMarketIndexCandles getMarketIndexCandles() {
    return new GetMarketIndexCandles(actor, context);
  }

  public GetMarketMarkPriceCandles getMarketMarkPriceCandles() {
    return new GetMarketMarkPriceCandles(actor, context);
  }

  public GetMarketTickers getMarketTickers() {
    return new GetMarketTickers(actor, context);
  }

  public GetMarketTrades getMarketTrades() {
    return new GetMarketTrades(actor, context);
  }

  public GetPublicInstruments getPublicInstruments() {
    return new GetPublicInstruments(actor, context);
  }
}
