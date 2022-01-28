package io.contek.invoker.okx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketRuntimeException;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  protected WebSocketApi(IActor actor) {
    super(
        actor,
        WebSocketMessageParser.getInstance(),
        new WebSocketAuthenticator(actor.getCredential(), actor.getClock()),
        IWebSocketLiveKeeper.noOp());
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}
}
