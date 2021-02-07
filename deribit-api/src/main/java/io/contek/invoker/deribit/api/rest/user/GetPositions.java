package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common.Deposit;
import io.contek.invoker.deribit.api.common._Position;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

public class GetPositions extends UserRestRequest<GetPositions.Response> {
  private String currency;
  private String kind;

  GetPositions(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPositions setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public GetPositions setKind(String kind) {
    this.kind = kind;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/get_positions";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(currency);
    builder.add("currency", currency);

    if (kind != null) {
      builder.add("kind", kind);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }


  public static final class Result {
    public int count;
    public List<Deposit> data;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Position>> {
  }
}
