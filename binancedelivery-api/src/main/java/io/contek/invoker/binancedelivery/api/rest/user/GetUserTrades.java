package io.contek.invoker.binancedelivery.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._UserTrade;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetUserTrades extends UserRestRequest<GetUserTrades.Response> {

  public static final int DEFAULT_LIMIT = 50;
  public static final int MAX_LIMIT = 100;

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA_WITH_SYMBOL =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(20));
  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA_WITHOUT_SYMBOL =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(40));

  private String symbol;
  private String pair;
  private Long startTime;
  private Long endTime;
  private Long fromId;
  private Integer limit;

  GetUserTrades(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetUserTrades setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetUserTrades setPair(@Nullable String pair) {
    this.pair = pair;
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
    return "/dapi/v1/userTrades";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (pair != null) {
      builder.add("pair", pair);
    }
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
    if (symbol != null) {
      return REQUIRED_QUOTA_WITH_SYMBOL;
    }
    if (pair != null) {
      return REQUIRED_QUOTA_WITHOUT_SYMBOL;
    }
    throw new IllegalStateException();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_UserTrade> {}
}
