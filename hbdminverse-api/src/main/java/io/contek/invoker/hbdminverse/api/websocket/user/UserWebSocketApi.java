package io.contek.invoker.hbdminverse.api.websocket.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

@ThreadSafe
public final class UserWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        WebSocketMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/swap-notification");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
