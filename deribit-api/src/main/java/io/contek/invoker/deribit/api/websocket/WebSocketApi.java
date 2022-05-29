package io.contek.invoker.deribit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;

public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final WebSocketRequestIdGenerator requestIdGenerator;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    this(actor, context, new WebSocketMessageParser());
  }

  private WebSocketApi(IActor actor, WebSocketContext context, WebSocketMessageParser parser) {
    this(actor, context, parser, new WebSocketRequestIdGenerator(parser));
  }

  private WebSocketApi(
      IActor actor,
      WebSocketContext context,
      WebSocketMessageParser parser,
      WebSocketRequestIdGenerator requestIdGenerator) {
    super(
        actor,
        parser,
        new WebSocketAuthenticator(actor.getCredential(), requestIdGenerator, actor.getClock()),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
    this.requestIdGenerator = requestIdGenerator;
  }

  public WebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_WEB_SOCKET_CONNECTION;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/api/v2");
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}
}
