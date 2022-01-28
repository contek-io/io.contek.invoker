package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Market;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class GetFutures extends MarketRestRequest<GetFutures.Response> {

  GetFutures(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/futures";
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
  public static final class Response extends RestResponse<List<_Market>> {}
}
