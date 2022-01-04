package io.contek.invoker.ftx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
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
        new WebSocketAuthenticator(actor.getCredential(), actor.getClock()),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {
    if (message instanceof WebSocketInfoMessage) {
      WebSocketInfoMessage info = (WebSocketInfoMessage) message;
      String msg = info.code + ": " + info.msg;
      if (info.code == 20001) {
        throw new WebSocketServerRestartException(msg);
      }
      if (info.code == 400) {
        throw new WebSocketAuthenticationException(msg);
      }
      throw new WebSocketIllegalMessageException(msg);
    }
  }
}
