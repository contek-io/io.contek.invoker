package io.contek.invoker.binancefutures.api.rest.user;

import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._PositionRisk;
import io.contek.invoker.binancefutures.api.rest.user.GetPositionRisk.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import java.util.ArrayList;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetPositionRisk extends UserRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(5));

  private String symbol;

  GetPositionRisk(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPositionRisk setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
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
    return "/fapi/v2/positionRisk";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_PositionRisk> {}
}
