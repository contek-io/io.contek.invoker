package io.contek.invoker.binancespot.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;

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

  public GetOrder getOrder() {
    return new GetOrder(actor, context);
  }

  public PostOrder postOrder() {
    return new PostOrder(actor, context);
  }

  public PostListenKey postListenKey() {
    return new PostListenKey(actor, context);
  }

  public PutListenKey putListenKey() {
    return new PutListenKey(actor, context);
  }
}
