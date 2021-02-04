package io.contek.invoker.deribit.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;

public final class GetCancelAll extends UserRestRequest<GetCancelAll.Response> {

  GetCancelAll(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected Class<GetCancelAll.Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/cancel_all";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Integer> {
  }
}
