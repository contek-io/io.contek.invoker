package io.contek.invoker.deribit.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.rest.common.RestResponse;
import io.contek.invoker.deribit.api.common._Position;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetPositions extends UserRestRequest<GetPositions.Response> {

  private Boolean showAvgPrice;

  GetPositions(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPositions setShowAvgPrice(Boolean showAvgPrice) {
    this.showAvgPrice = showAvgPrice;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/positions";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (showAvgPrice != null) {
      builder.add("showAvgPrice", showAvgPrice);
    }
    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Position>> {}
}
