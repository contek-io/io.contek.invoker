package io.contek.invoker.binancefutures.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._UserTrade;
import io.contek.invoker.binancefutures.api.rest.user.GetUserTrades.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetUserTrades extends UserRestRequest<Response> {

  public static final int DEFAULT_LIMIT = 500;
  public static final int MAX_LIMIT = 1000;

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(5));

  private String symbol;
  private Long startTime;
  private Long endTime;
  private Long fromId;
  private Integer limit;

  GetUserTrades(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetUserTrades setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetUserTrades setStartTime(@Nullable Long startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetUserTrades setEndTime(@Nullable Long endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetUserTrades setFromId(@Nullable Long fromId) {
    this.fromId = fromId;
    return this;
  }

  public GetUserTrades setLimit(@Nullable Integer limit) {
    this.limit = limit;
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
    return "/fapi/v1/userTrades";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }
    if (fromId != null) {
      builder.add("fromId", fromId);
    }
    if (limit != null) {
      builder.add("limit", limit);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_UserTrade> {}
}
