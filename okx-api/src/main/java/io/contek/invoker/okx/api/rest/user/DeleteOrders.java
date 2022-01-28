package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class DeleteOrders extends UserRestRequest<DeleteOrders.Response> {

  private String order_id;

  DeleteOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public DeleteOrders setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(order_id);
    return format("/api/orders/{0}", order_id);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
