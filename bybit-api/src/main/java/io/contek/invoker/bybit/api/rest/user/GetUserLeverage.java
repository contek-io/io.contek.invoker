package io.contek.invoker.bybit.api.rest.user;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_READ_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetUserLeverage.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._Leverage;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import java.util.Map;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetUserLeverage extends UserRestRequest<Response> {

  GetUserLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/user/leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_PRIVATE_POSITION_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Map<String, _Leverage>> {}
}
