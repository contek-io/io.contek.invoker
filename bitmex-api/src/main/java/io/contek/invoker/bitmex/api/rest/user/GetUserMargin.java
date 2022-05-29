package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Margin;
import io.contek.invoker.bitmex.api.rest.user.GetUserMargin.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import static io.contek.invoker.commons.rest.RestMethod.GET;

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

  public static final class Response extends _Margin {}
}
