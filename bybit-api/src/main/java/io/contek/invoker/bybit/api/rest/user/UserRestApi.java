package io.contek.invoker.bybit.api.rest.user;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserRestApi {

  private final IActor actor;
  private final RestContext context;

  public UserRestApi(IActor actor, RestContext context) {
    this.actor = actor;
    this.context = context;
  }

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public GetOrderList getOrderList() {
    return new GetOrderList(actor, context);
  }

  public GetPositionList getPositionList() {
    return new GetPositionList(actor, context);
  }

  public PostOrderCancel postOrderCancel() {
    return new PostOrderCancel(actor, context);
  }

  public PostOrderCreate postOrderCreate() {
    return new PostOrderCreate(actor, context);
  }
}
