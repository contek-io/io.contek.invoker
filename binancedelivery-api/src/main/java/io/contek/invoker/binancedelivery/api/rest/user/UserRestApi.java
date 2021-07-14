package io.contek.invoker.binancedelivery.api.rest.user;

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

  public DeleteAllOpenOrders deleteAllOpenOrders() {
    return new DeleteAllOpenOrders(actor, context);
  }

  public DeleteOrder deleteOrder() {
    return new DeleteOrder(actor, context);
  }

  public GetAccount getAccount() {
    return new GetAccount(actor, context);
  }

  public GetAllOrders getAllOrders() {
    return new GetAllOrders(actor, context);
  }

  public GetOpenOrders getOpenOrders() {
    return new GetOpenOrders(actor, context);
  }

  public PostLeverage postLeverage() {
    return new PostLeverage(actor, context);
  }

  public PostMarginType postMarginType() {
    return new PostMarginType(actor, context);
  }

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public GetPositionRisk getPositionRisk() {
    return new GetPositionRisk(actor, context);
  }

  public GetPositionSideDual getPositionSideDual() {
    return new GetPositionSideDual(actor, context);
  }

  public PostOrder postOrder() {
    return new PostOrder(actor, context);
  }

  public PostPositionSideDual postPositionSideDual() {
    return new PostPositionSideDual(actor, context);
  }

  public PostListenKey postListenKey() {
    return new PostListenKey(actor, context);
  }

  public PutListenKey putListenKey() {
    return new PutListenKey(actor, context);
  }
}
