package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._UserTradingRecords;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_TRADE_READ_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetExecutionList.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetExecutionList extends UserRestRequest<Response> {

  private String symbol;
  private String order_id;
  private Long start_time;
  private Integer page;
  private Integer limit;
  private String order;

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
    return "/v2/private/execution/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (order_id != null) {
      builder.add("order_id", order_id);
    }

    if (start_time != null) {
      builder.add("start_time", start_time);
    }

    if (page != null) {
      builder.add("page", page);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    if (order != null) {
      builder.add("order", order);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_PRIVATE_TRADE_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_UserTradingRecords> {}
}
