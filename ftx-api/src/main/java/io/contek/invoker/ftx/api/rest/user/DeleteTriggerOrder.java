package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

public final class DeleteTriggerOrder extends UserRestRequest<DeleteTriggerOrder.Response> {

  private Long id;

  DeleteTriggerOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Long getId() {
    return id;
  }

  public DeleteTriggerOrder setId(Long id) {
    this.id = id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(id);
    return "/api/conditional_orders/" + id;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<DeleteTriggerOrder.Response> getResponseType() {
    return DeleteTriggerOrder.Response.class;
  }

  public static final class Response extends RestResponse<String> {}
}
