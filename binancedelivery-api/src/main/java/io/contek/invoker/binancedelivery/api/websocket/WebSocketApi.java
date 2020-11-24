package io.contek.invoker.binancedelivery.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.api.websocket.IWebSocketAuthenticator;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketRequestIdGenerator requestIdGenerator = new WebSocketRequestIdGenerator();

  protected WebSocketApi(IActor actor) {
    super(actor, WebSocketMessageParser.getInstance(), IWebSocketAuthenticator.noOp());
  }

  public final WebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ImmutableList.of();
  }
}
