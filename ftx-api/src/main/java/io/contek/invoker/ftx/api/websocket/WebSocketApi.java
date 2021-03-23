package io.contek.invoker.ftx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.commons.websocket.WebSocketServerRestartException;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInfoMessage;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

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
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message) {
    if (message instanceof WebSocketInfoMessage) {
      WebSocketInfoMessage info = (WebSocketInfoMessage) message;
      if (info.code == 20001) {
        throw new WebSocketServerRestartException(info.code + ": " + info.msg);
      }
    }
  }
}
