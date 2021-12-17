package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.common._UserTradingRecords;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_TRADE_READ_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.GetExecutionList.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetExecutionList extends UserRestRequest<Response> {

  private String symbol;
  private Long start_time;
  private Long end_time;
  private String exec_type;
  private Integer page;
  private Integer limit;

  GetExecutionList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetExecutionList setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/private/linear/trade/execution/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (start_time != null) {
      builder.add("start_time", start_time);
    }

    if (end_time != null) {
      builder.add("end_time", end_time);
    }

    if (exec_type != null) {
      builder.add("exec_type", exec_type);
    }

    if (page != null) {
      builder.add("page", page);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_TRADE_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_UserTradingRecords> {}
}
