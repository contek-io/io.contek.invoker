package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Balance;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

@NotThreadSafe
public class GetWithdrawals extends OTCWithdrawalsRestRequest<GetWithdrawals.Response> {

  private Long startTime;
  private Long endTime;
  private Integer limit;

  public GetWithdrawals(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Long getStartTime() {
    return startTime;
  }

  public GetWithdrawals setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public Long getEndTime() {
    return endTime;
  }

  public GetWithdrawals setEndTime(Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public Integer getLimit() {
    return limit;
  }

  public GetWithdrawals setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
  }

  @Override
  protected String getEndpointPathOTC() {
    return "";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (startTime != null) {
      builder.add("start_time", startTime);
    }

    if (endTime != null) {
      builder.add("end_time", endTime);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }
    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Map<String, _Balance>> {}
}
