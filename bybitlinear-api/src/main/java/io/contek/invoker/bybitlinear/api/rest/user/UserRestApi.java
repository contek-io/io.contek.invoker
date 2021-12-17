package io.contek.invoker.bybitlinear.api.rest.user;

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

  public GetApiKey getApiKey() {
    return new GetApiKey(actor, context);
  }

  public GetExecutionList getExecutionList() {
    return new GetExecutionList(actor, context);
  }

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public GetOrderList getOrderList() {
    return new GetOrderList(actor, context);
  }

  public GetPosition getPosition() {
    return new GetPosition(actor, context);
  }

  public GetPositionList getPositionList() {
    return new GetPositionList(actor, context);
  }

  public GetUserLeverage getUserLeverage() {
    return new GetUserLeverage(actor, context);
  }

  public GetWalletBalance getWalletBalance() {
    return new GetWalletBalance(actor, context);
  }

  public GetWalletFundRecords getWalletFundRecords() {
    return new GetWalletFundRecords(actor, context);
  }

  public PostLeverageSave postLeverageSave() {
    return new PostLeverageSave(actor, context);
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

  public PostUserLeverageSave postUserLeverageSave() {
    return new PostUserLeverageSave(actor, context);
  }
}
