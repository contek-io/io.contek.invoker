package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Margin;
import io.contek.invoker.bitmex.api.rest.user.GetUserMargin.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.api.rest.RestMethod.GET;

@NotThreadSafe
public final class GetUserMargin extends UserRestRequest<Response> {

  GetUserMargin(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/user/margin";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @NotThreadSafe
  public static final class Response extends _Margin {}
}
