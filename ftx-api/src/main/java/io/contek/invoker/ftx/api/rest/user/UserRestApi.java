package io.contek.invoker.ftx.api.rest.user;

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

  public GetOrders getOrders() {
    return new GetOrders(actor, context);
  }

  public GetOrdersByClientId getOrdersByClientId() {
    return new GetOrdersByClientId(actor, context);
  }

  public ModifyOrders modifyOrders() {
    return new ModifyOrders(actor, context);
  }

  public ModifyOrdersByClientId modifyOrdersByClientId() {
    return new ModifyOrdersByClientId(actor, context);
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

  public GetLendingRates getLendingRates() {
    return new GetLendingRates(actor, context);
  }

  public GetBorrowRates getBorrowRates() {
    return new GetBorrowRates(actor, context);
  }

  public GetDailyBorrowedAmounts getDailyBorrowedAmounts() {
    return new GetDailyBorrowedAmounts(actor, context);
  }

  public GetBorrowHistory getBorrowHistory() {
    return new GetBorrowHistory(actor, context);
  }

  public GetLendingHistory getLendingHistory() {
    return new GetLendingHistory(actor, context);
  }

  public GetLendingOffers getLendingOffers() {
    return new GetLendingOffers(actor, context);
  }

  public GetLendingInfo getLendingInfo() {
    return new GetLendingInfo(actor, context);
  }

  public SubmitLendingOffer submitLendingOffer() {
    return new SubmitLendingOffer(actor, context);
  }

  public PostRequestQuote postRequestQuote() {
    return new PostRequestQuote(actor, context);
  }

  public PostAcceptQuote postAcceptQuote() {
    return new PostAcceptQuote(actor, context);
  }

  public GetAllSubAccounts getAllSubAccounts() {
    return new GetAllSubAccounts(actor, context);
  }

  public PostTransferBetweenSubAccounts postTransferBetweenSubAccounts() {
    return new PostTransferBetweenSubAccounts(actor, context);
  }

  public PostTriggerOrder postTriggerOrder() {
    return new PostTriggerOrder(actor, context);
  }

  public DeleteTriggerOrder deleteTriggerOrder() {
    return new DeleteTriggerOrder(actor, context);
  }

  public GetFundingPayments getFundingPayments() {
    return new GetFundingPayments(actor, context);
  }
}
