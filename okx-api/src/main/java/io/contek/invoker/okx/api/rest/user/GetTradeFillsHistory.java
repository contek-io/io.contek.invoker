package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Fill;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetTradeFillsHistory extends UserRestRequest<GetTradeFillsHistory.Response> {

  private String instType;
  private String uly;
  private String instId;
  private String ordId;
  private Long after;
  private Long before;
  private Integer limit;

  GetTradeFillsHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeFillsHistory setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetTradeFillsHistory setUly(@Nullable String uly) {
    this.uly = uly;
    return this;
  }

  public GetTradeFillsHistory setInstId(@Nullable String instId) {
    this.instId = instId;
    return this;
  }

  public GetTradeFillsHistory setOrdId(@Nullable String ordId) {
    this.ordId = ordId;
    return this;
  }

  public GetTradeFillsHistory setAfter(@Nullable Long after) {
    this.after = after;
    return this;
  }

  public GetTradeFillsHistory setBefore(@Nullable Long before) {
    this.before = before;
    return this;
  }

  public GetTradeFillsHistory setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected Class<GetTradeFillsHistory.Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/fills-history";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instType);
    builder.add("instType", instType);

    if (uly != null) {
      builder.add("uly", uly);
    }

    if (instId != null) {
      builder.add("instId", instId);
    }

    if (ordId != null) {
      builder.add("ordId", ordId);
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

  @NotThreadSafe
  public static final class Response extends RestResponse<_Fill> {}
}
