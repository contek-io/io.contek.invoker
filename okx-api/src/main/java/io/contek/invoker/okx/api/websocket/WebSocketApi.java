package io.contek.invoker.okx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.okx.api.websocket.common.WebSocketGeneralResponse;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._error;

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
      throws WebSocketRuntimeException {
    if (message instanceof WebSocketGeneralResponse) {
      WebSocketGeneralResponse response = (WebSocketGeneralResponse) message;
      if (_error.equals(response.event)) {
        throw new WebSocketIllegalStateException(response.code + ": " + response.msg);
      }
    }
  }
}
