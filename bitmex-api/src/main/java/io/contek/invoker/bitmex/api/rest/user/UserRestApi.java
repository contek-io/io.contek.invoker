package io.contek.invoker.bitmex.api.rest.user;

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

  public DeleteOrder deleteOrder() {
    return new DeleteOrder(actor, context);
  }

  public DeleteOrderAll deleteOrderAll() {
    return new DeleteOrderAll(actor, context);
  }

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public GetPosition getPosition() {
    return new GetPosition(actor, context);
  }

  public GetUserMargin getUserMargin() {
    return new GetUserMargin(actor, context);
  }

  public PostOrder postOrder() {
    return new PostOrder(actor, context);
  }

  public PostPositionIsolate postPositionIsolate() {
    return new PostPositionIsolate(actor, context);
  }

  public PostPositionLeverage postPositionLeverage() {
    return new PostPositionLeverage(actor, context);
  }

  public PostPositionRiskLimit postPositionRiskLimit() {
    return new PostPositionRiskLimit(actor, context);
  }
}
