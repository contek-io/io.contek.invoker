package io.contek.invoker.kraken.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    this(new WebSocketRequestIdGenerator(), actor, context);
  }

  private WebSocketApi(
      WebSocketRequestIdGenerator requestIdGenerator, IActor actor, WebSocketContext context) {
    super(
        actor,
        WebSocketMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        new WebSocketLiveKeeper(requestIdGenerator, actor.clock()));
    this.context = context;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ImmutableList.of();
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.baseUrl());
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}

  protected final WebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }
}
