package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetOpenOrders extends UserRestRequest<GetOpenOrders.Response> {

  private String market;

  GetOpenOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOpenOrders setMarket(String market) {
    this.market = market;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/orders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }
    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Order>> {}
}
