package io.contek.invoker.ftx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.websocket.*;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInfoMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, WebSocketMessageParser.getInstance(), IWebSocketAuthenticator.noOp());
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
