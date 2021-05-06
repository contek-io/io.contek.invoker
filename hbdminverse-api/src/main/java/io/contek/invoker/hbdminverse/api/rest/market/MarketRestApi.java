package io.contek.invoker.hbdminverse.api.rest.market;

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

  public GetMarketDepth getMarketDepth() {
    return new GetMarketDepth(actor, context);
  }

  public GetMarketDetailMerged getMarketDetailMerged() {
    return new GetMarketDetailMerged(actor, context);
  }

  public GetMarketDetailBatchMerged getMarketDetailBatchMerged() {
    return new GetMarketDetailBatchMerged(actor, context);
  }

  public GetSwapContractInfo getSwapContractInfo() {
    return new GetSwapContractInfo(actor, context);
  }
}
