package io.contek.invoker.okx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetAccountAccountPositionRisk getAccountAccountPositionRisk() {
    return new GetAccountAccountPositionRisk(actor, context);
  }

  public GetAccountBalance getAccountBalance() {
    return new GetAccountBalance(actor, context);
  }

  public GetAccountConfig getAccountConfig() {
    return new GetAccountConfig(actor, context);
  }

  public GetAccountPositions getAccountPositions() {
    return new GetAccountPositions(actor, context);
  }

  public GetTradeFills getTradeFills() {
    return new GetTradeFills(actor, context);
  }

  public GetTradeFillsHistory getTradeFillsHistory() {
    return new GetTradeFillsHistory(actor, context);
  }

  public GetTradeOrder getTradeOrder() {
    return new GetTradeOrder(actor, context);
  }

  public GetTradeOrderHistory getTradeOrderHistory() {
    return new GetTradeOrderHistory(actor, context);
  }

  public GetTradeOrderHistoryArchive getTradeOrderHistoryArchive() {
    return new GetTradeOrderHistoryArchive(actor, context);
  }

  public GetTradeOrdersPending getTradeOrdersPending() {
    return new GetTradeOrdersPending(actor, context);
  }

  public PostAccountSetLeverage postAccountSetLeverage() {
    return new PostAccountSetLeverage(actor, context);
  }

  public PostAccountSetPositionMode postAccountSetPositionMode() {
    return new PostAccountSetPositionMode(actor, context);
  }

  public PostTradeCancelOrder postTradeCancelOrder() {
    return new PostTradeCancelOrder(actor, context);
  }

  public PostTradeOrder postTradeOrder() {
    return new PostTradeOrder(actor, context);
  }
}
