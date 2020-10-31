package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.api.rest.RestMethod.DELETE;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class DeleteOrdersByClientOrderId
    extends UserRestRequest<DeleteOrdersByClientOrderId.Response> {

  private String by_client_id;

  DeleteOrdersByClientOrderId(IActor actor, RestContext context) {
    super(actor, context);
  }

  public DeleteOrdersByClientOrderId setByClientId(String by_client_id) {
    this.by_client_id = by_client_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(by_client_id);
    return format("/api/orders/by_client_id/{0}", by_client_id);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
