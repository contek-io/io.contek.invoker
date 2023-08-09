package io.contek.invoker.bybit.api.rest.user;

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

  public GetExecutionList getExecutionList() {
    return new GetExecutionList(actor, context);
  }

  public GetOrderHistory getOrderHistory() {
    return new GetOrderHistory(actor, context);
  }

  public GetOrderRealtime getOrderRealtime() {
    return new GetOrderRealtime(actor, context);
  }

  public GetPositionList getPositionList() {
    return new GetPositionList(actor, context);
  }

  public GetWalletBalance getWalletBalance() {
    return new GetWalletBalance(actor, context);
  }

  public PostPositionSwitchIsolated postPositionSwitchIsolated() {
    return new PostPositionSwitchIsolated(actor, context);
  }

  public PostPositionSwitchMode postPositionSwitchMode() {
    return new PostPositionSwitchMode(actor, context);
  }

  public PostPositionSetLeverage postPositionSetLeverage() {
    return new PostPositionSetLeverage(actor, context);
  }

  public PostOrderCancel postOrderCancel() {
    return new PostOrderCancel(actor, context);
  }

  public PostOrderCancelAll postOrderCancelAll() {
    return new PostOrderCancelAll(actor, context);
  }

  public PostOrderCreate postOrderCreate() {
    return new PostOrderCreate(actor, context);
  }
}
