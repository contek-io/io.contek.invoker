package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Future;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetFuture extends MarketRestRequest<GetFuture.Response> {

  private String future_name;

  GetFuture(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetFuture setFutureName(String future_name) {
    this.future_name = future_name;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(future_name);
    return format("/api/futures/{0}", future_name);
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
  public static final class Response extends RestResponse<_Future> {}
}
