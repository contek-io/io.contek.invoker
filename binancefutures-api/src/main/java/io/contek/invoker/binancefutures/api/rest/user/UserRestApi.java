package io.contek.invoker.binancefutures.api.rest.user;

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

  public DeleteAllOpenOrders deleteAllOpenOrders() {
    return new DeleteAllOpenOrders(actor, context);
  }

  public DeleteOrder deleteOrder() {
    return new DeleteOrder(actor, context);
  }

  public GetAccount getAccount() {
    return new GetAccount(actor, context);
  }

  public GetOpenOrders getOpenOrders() {
    return new GetOpenOrders(actor, context);
  }

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public GetPositionRisk getPositionRisk() {
    return new GetPositionRisk(actor, context);
  }

  public PostOrder postOrder() {
    return new PostOrder(actor, context);
  }

  public PostStream postStream() {
    return new PostStream(actor, context);
  }

  public PutStream putStream() {
    return new PutStream(actor, context);
  }
}
