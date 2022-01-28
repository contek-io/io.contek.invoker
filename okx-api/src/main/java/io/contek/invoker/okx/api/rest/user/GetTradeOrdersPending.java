package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Order;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetTradeOrdersPending extends UserRestRequest<GetTradeOrdersPending.Response> {

  private String instType;
  private String uly;
  private String instId;
  private String ordType;
  private String state;
  private Long after;
  private Long before;
  private Integer limit;

  GetTradeOrdersPending(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeOrdersPending setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetTradeOrdersPending setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetTradeOrdersPending setUly(String uly) {
    this.uly = uly;
    return this;
  }

  public GetTradeOrdersPending setOrdType(String ordType) {
    this.ordType = ordType;
    return this;
  }

  public GetTradeOrdersPending setState(String state) {
    this.state = state;
    return this;
  }

  public GetTradeOrdersPending setAfter(Long after) {
    this.after = after;
    return this;
  }

  public GetTradeOrdersPending setBefore(Long before) {
    this.before = before;
    return this;
  }

  public GetTradeOrdersPending setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/orders-pending";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (instType != null) {
      builder.add("instType", instType);
    }

    if (uly != null) {
      builder.add("uly", uly);
    }

    if (instId != null) {
      builder.add(instId, instId);
    }

    if (ordType != null) {
      builder.add("ordType", ordType);
    }

    if (state != null) {
      builder.add("state", state);
    }

    if (after != null) {
      builder.add("after", after);
    }

    if (before != null) {
      builder.add("before", before);
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

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Order>> {}
}
