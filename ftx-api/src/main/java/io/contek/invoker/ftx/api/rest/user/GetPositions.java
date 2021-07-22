package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Position;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class GetPositions extends UserRestRequest<GetPositions.Response> {

  private Boolean showAvgPrice;

  public GetPositions(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Boolean getShowAvgPrice() {
    return showAvgPrice;
  }

  public GetPositions setShowAvgPrice(Boolean showAvgPrice) {
    this.showAvgPrice = showAvgPrice;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
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
