package io.contek.invoker.deribit.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;

@NotThreadSafe
public final class DeleteAllOrders extends UserRestRequest<DeleteAllOrders.Response> {

  private String market;
  private Boolean conditionalOrdersOnly;
  private Boolean limitOrdersOnly;

  DeleteAllOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/orders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }

    if (conditionalOrdersOnly != null) {
      builder.add("conditionalOrdersOnly", conditionalOrdersOnly);
    }

    if (limitOrdersOnly != null) {
      builder.add("limitOrdersOnly", limitOrdersOnly);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
