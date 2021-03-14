package io.contek.invoker.binancedelivery.api.rest.user;

import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._PositionMode;
import io.contek.invoker.binancedelivery.api.rest.user.GetPositionSideDual.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetPositionSideDual extends UserRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(30));

  GetPositionSideDual(IActor actor, RestContext context) {
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
    return "/dapi/v1/positionSide/dual";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends _PositionMode {}
}
