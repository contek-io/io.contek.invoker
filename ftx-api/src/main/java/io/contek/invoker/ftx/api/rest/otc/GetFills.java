package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Fill;
import io.contek.invoker.ftx.api.rest.RestRequest;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class GetFills extends RestRequest<GetFills.Response> {

  private Integer limit;

  public GetFills(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Integer getLimit() {
    return limit;
  }

  public GetFills setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
  }

  @Override
  protected String getEndpointPath() {
    return limit != null ? "/api/fills?limit=" + limit : "/api/fills";
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
  public static final class Response extends RestResponse<_Fill> {}
}
