package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApiOTC {

  private final IActor actor;
  private final RestContext context;

  public UserRestApiOTC(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public CancelRequestedWithdrawal cancelRequestedWithdrawal() {
    return new CancelRequestedWithdrawal(actor, context);
  }

  public GetBalances getBalances() {
    return new GetBalances(actor, context);
  }

  public GetCounterpartySettlements getCounterpartySettlements() {
    return new GetCounterpartySettlements(actor, context);
  }

  public GetDeferCostPayments getDeferCostPayments() {
    return new GetDeferCostPayments(actor, context);
  }

  public GetDeferProceedsPayments getDeferProceedsPayments() {
    return new GetDeferProceedsPayments(actor, context);
  }

  public GetFills getFills() {
    return new GetFills(actor, context);
  }

  public GetRecentAcceptedQuotes getRecentAcceptedQuotes() {
    return new GetRecentAcceptedQuotes(actor, context);
  }

  public GetRecentQuotes getRecentQuotes() {
    return new GetRecentQuotes(actor, context);
  }

  public GetRequestedQuote getRequestedQuote() {
    return new GetRequestedQuote(actor, context);
  }

  public GetSettlements getSettlements() {
    return new GetSettlements(actor, context);
  }

  public GetWithdrawals getWithdrawals() {
    return new GetWithdrawals(actor, context);
  }

  public PostAcceptQuote postAcceptQuote() {
    return new PostAcceptQuote(actor, context);
  }

  public PostRequestQuote postRequestQuote() {
    return new PostRequestQuote(actor, context);
  }

  public PostRequestWithdrawal postRequestWithdrawal() {
    return new PostRequestWithdrawal(actor, context);
  }

  public PostSetSettlementPriority postSetSettlementPriority() {
    return new PostSetSettlementPriority(actor, context);
  }
}
