package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.api.websocket.WebSocketCall;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        WebSocketMessageParser.getInstance(),
        new WebSocketAuthenticator(actor.getCredential(), actor.getClock()));
    this.context = context;
  }

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_WEB_SOCKET_CONNECTION;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/realtime");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message) {}
}
