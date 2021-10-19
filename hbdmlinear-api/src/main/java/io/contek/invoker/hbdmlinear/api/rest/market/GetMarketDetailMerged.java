package io.contek.invoker.hbdmlinear.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdmlinear.api.common._MarketDetail;
import io.contek.invoker.hbdmlinear.api.rest.common.RestChannelTickResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;

@NotThreadSafe
public final class GetMarketDetailMerged extends MarketRestRequest<GetMarketDetailMerged.Response> {

  private String contract_code;

  GetMarketDetailMerged(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketDetailMerged setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/linear-swap-ex/market/detail/merged";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    Objects.requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestChannelTickResponse<_MarketDetail> {}
}
