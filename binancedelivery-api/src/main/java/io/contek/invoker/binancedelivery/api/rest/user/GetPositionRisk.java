package io.contek.invoker.binancedelivery.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._PositionRisk;
import io.contek.invoker.binancedelivery.api.rest.user.GetPositionRisk.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetPositionRisk extends UserRestRequest<Response> {

  private String marginAsset;
  private String pair;

  GetPositionRisk(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPositionRisk setMarginAsset(String marginAsset) {
    this.marginAsset = marginAsset;
    return this;
  }

  public GetPositionRisk setPair(String pair) {
    this.pair = pair;
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
    return "/dapi/v1/positionRisk";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (marginAsset != null) {
      builder.add("marginAsset", marginAsset);
    }

    if (pair != null) {
      builder.add("pair", pair);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_PositionRisk> {}
}
