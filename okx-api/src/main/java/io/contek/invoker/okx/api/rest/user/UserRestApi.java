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

  public PostTradeCancelOrder postTradeCancelOrder() {
    return new PostTradeCancelOrder(actor, context);
  }

  public GetAccount getAccount() {
    return new GetAccount(actor, context);
  }

  public GetTradeOrdersPending getTradeOrdersPending() {
    return new GetTradeOrdersPending(actor, context);
  }

  public GetOrderHistory getOrderHistory() {
    return new GetOrderHistory(actor, context);
  }

  public GetPositions getPositions() {
    return new GetPositions(actor, context);
  }

  public GetWalletBalances getWalletBalances() {
    return new GetWalletBalances(actor, context);
  }

  public GetWalletAllBalances getWalletAllBalances() {
    return new GetWalletAllBalances(actor, context);
  }

  public PostAccountSetLeverage postAccountLeverage() {
    return new PostAccountSetLeverage(actor, context);
  }

  public PostTradeOrder postTradeOrder() {
    return new PostTradeOrder(actor, context);
  }

  public GetTradeFills getTradeFills() {
    return new GetTradeFills(actor, context);
  }
}
