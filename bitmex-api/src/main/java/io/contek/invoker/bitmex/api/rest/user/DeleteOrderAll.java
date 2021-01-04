package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.rest.user.DeleteOrderAll.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;

@NotThreadSafe
public final class DeleteOrderAll extends UserRestRequest<Response> {

  private String symbol;

  DeleteOrderAll(IActor actor, RestContext context) {
    super(actor, context);
  }

  public DeleteOrderAll setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/order/all";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
