package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.rest.user.DeleteOrder.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static io.contek.invoker.commons.rest.RestMethod.DELETE;

public final class DeleteOrder extends UserRestRequest<Response> {

  private final Set<String> orderID = new HashSet<>();
  private final Set<String> clOrdID = new HashSet<>();

  DeleteOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public DeleteOrder addOrderID(String orderID) {
    this.orderID.add(orderID);
    return this;
  }

  public DeleteOrder addClOrdID(String clOrdID) {
    this.clOrdID.add(clOrdID);
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
    return "/api/v1/order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkArgument(!orderID.isEmpty() || !clOrdID.isEmpty());

    if (!orderID.isEmpty()) {
      String value = String.join(",", orderID);
      builder.add("orderID", value);
    }
    if (!clOrdID.isEmpty()) {
      String value = String.join(",", clOrdID);
      builder.add("clOrdID", value);
    }

    return builder.build();
  }

  public static final class Response extends ArrayList<_Order> {}
}
