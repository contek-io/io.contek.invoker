package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

public final class DeleteTriggerOrder extends UserRestRequest<PostTriggerOrder.Response> {

  private Long id;

  public DeleteTriggerOrder(IActor actor, RestContext context) {
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
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(id);
    return "/api/conditional_orders/" + id;
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<PostTriggerOrder.Response> getResponseType() {
    return PostTriggerOrder.Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
