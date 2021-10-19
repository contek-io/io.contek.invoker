package io.contek.invoker.hbdminverse.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._Depth;
import io.contek.invoker.hbdminverse.api.rest.common.RestChannelTickResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketDepth extends MarketRestRequest<GetMarketDepth.Response> {

  private String contract_code;
  private String type;

  GetMarketDepth(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketDepth setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  public GetMarketDepth setType(String type) {
    this.type = type;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-ex/market/depth";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    requireNonNull(type);
    builder.add("type", type);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestChannelTickResponse<_Depth> {}
}
