package io.contek.invoker.okx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.okx.api.common._LendingOffer;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public DeleteAllOrders deleteAllOrders() {
    return new DeleteAllOrders(actor, context);
  }

  public DeleteOrders deleteOrders() {
    return new DeleteOrders(actor, context);
  }

  public DeleteOrdersByClientOrderId deleteOrdersByClientOrderId() {
    return new DeleteOrdersByClientOrderId(actor, context);
  }

  public GetAccount getAccount() {
    return new GetAccount(actor, context);
  }

  public GetOpenOrders getOpenOrders() {
    return new GetOpenOrders(actor, context);
  }

  public GetOrderHistory getOrderHistory() {
    return new GetOrderHistory(actor, context);
  }

  public GetOrders getOrders() {
    return new GetOrders(actor, context);
  }

  public GetOrdersByClientId getOrdersByClientId() {
    return new GetOrdersByClientId(actor, context);
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

  public PostAccountLeverage postAccountLeverage() {
    return new PostAccountLeverage(actor, context);
  }

  public PostOrders postOrders() {
    return new PostOrders(actor, context);
  }

  public GetFills getFills() {
    return new GetFills(actor, context);
  }

  public GetLendingInfo getLendingInfo() {
    return new GetLendingInfo(actor, context);
  }

  public GetLendingOffers getLendingOffers() {
    return new GetLendingOffers(actor, context);
  }

  public PostLendingOffer postLendingOffer(final _LendingOffer lendingOffer) {
    return new PostLendingOffer(actor, context);
  }
}
