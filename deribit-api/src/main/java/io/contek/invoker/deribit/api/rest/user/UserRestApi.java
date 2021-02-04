package io.contek.invoker.deribit.api.rest.user;

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

  public GetBuy getBuy() {
    return new GetBuy(actor, context);
  }

  public GetSell getSell() {
    return new GetSell(actor, context);
  }

  public GetCancel getCancel() {
    return new GetCancel(actor, context);
  }

  public GetCancelAll getCancelAll() {
    return new GetCancelAll(actor, context);
  }

  public GetDeposits getDeposits() {
    return new GetDeposits(actor, context);
  }

}
