package io.contek.invoker.binancefutures.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.websocket.IWebSocketMessageParser;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  protected WebSocketApi(IActor actor, IWebSocketMessageParser parser) {
    super(actor, parser, IWebSocketAuthenticator.noOp());
  }

  public final WebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message) {}
}
