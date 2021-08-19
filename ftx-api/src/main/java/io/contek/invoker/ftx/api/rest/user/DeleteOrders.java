package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class DeleteOrders extends UserRestRequest<DeleteOrders.Response> {

  private String orderId;

  public DeleteOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getOrderId() {
    return orderId;
  }

  public DeleteOrders setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.DELETE;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(orderId);
    return format("/api/orders/{0}", orderId);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return null;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
