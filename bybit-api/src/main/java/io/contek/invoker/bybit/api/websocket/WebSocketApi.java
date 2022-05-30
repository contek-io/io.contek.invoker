package io.contek.invoker.bybit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    super(
        actor,
        new WebSocketMessageParser(),
        new WebSocketAuthenticator(actor.credential(), actor.clock()),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.baseUrl() + "/realtime");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}
}
