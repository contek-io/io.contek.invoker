package io.contek.invoker.binancefutures.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.rest.common.RestUpdateResponse;
import io.contek.invoker.binancefutures.api.rest.user.PostPositionSideDual.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.POST;

@NotThreadSafe
public final class PostPositionSideDual extends UserRestRequest<Response> {

  private Boolean dualSidePosition;

  PostPositionSideDual(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSideDual setDualSidePosition(Boolean dualSidePosition) {
    this.dualSidePosition = dualSidePosition;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/positionSide/dual";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();
    builder.add("dualSidePosition", Boolean.toString(dualSidePosition));
    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestUpdateResponse {}
}
