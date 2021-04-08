package io.contek.invoker.binancefutures.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.rest.user.PutListenKey.BinanceRestUserDataPutStreamResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.PUT;

@NotThreadSafe
public final class PutListenKey extends UserRestRequest<BinanceRestUserDataPutStreamResponse> {

  private String listenKey;

  PutListenKey(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PutListenKey setListenKey(String listenKey) {
    this.listenKey = listenKey;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return PUT;
  }

  @Override
  protected Class<BinanceRestUserDataPutStreamResponse> getResponseType() {
    return BinanceRestUserDataPutStreamResponse.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/listenKey";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(listenKey);
    builder.add("listenKey", listenKey);

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class BinanceRestUserDataPutStreamResponse {

    public String listenKey;
  }
}
