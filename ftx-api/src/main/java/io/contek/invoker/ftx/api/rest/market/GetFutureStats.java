package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._FutureStats;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public final class GetFutureStats extends MarketRestRequest<GetFutureStats.Response> {

  private String futureName;

  GetFutureStats(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getFutureName() {
    return futureName;
  }

  public GetFutureStats setFutureName(String futureName) {
    this.futureName = futureName;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    Objects.requireNonNull(futureName);
    return "/api/futures/" + futureName + "/stats";
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
  public static final class Response extends RestResponse<_FutureStats> {}
}
