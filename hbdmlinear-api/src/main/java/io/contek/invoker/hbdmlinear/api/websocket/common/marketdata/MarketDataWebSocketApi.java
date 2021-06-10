package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketLiveKeeper;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

@ThreadSafe
public abstract class MarketDataWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final MarketDataWebSocketRequestIdGenerator requestIdGenerator =
      new MarketDataWebSocketRequestIdGenerator();

  protected MarketDataWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        new MarketDataWebSocketMessageParser(),
        IWebSocketAuthenticator.noOp(),
        WebSocketLiveKeeper.getInstance());
    this.context = context;
  }

  protected final MarketDataWebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected final WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/linear-swap-ws");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}
}
