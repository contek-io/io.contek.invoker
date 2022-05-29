package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

public abstract class NotificationWebSocketApi extends BaseWebSocketApi {

  private static final String PATH = "/swap-notification";

  private final WebSocketContext context;
  private final NotificationWebSocketRequestIdGenerator requestIdGenerator;

  protected NotificationWebSocketApi(IActor actor, WebSocketContext context) {
    this(actor, context, new NotificationWebSocketRequestIdGenerator());
  }

  private NotificationWebSocketApi(
      IActor actor,
      WebSocketContext context,
      NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(
        actor,
        new NotificationWebSocketMessageParser(),
        new NotificationWebSocketAuthenticator(
            actor.getCredential(), PATH, requestIdGenerator, context, actor.getClock()),
        NotificationWebSocketLiveKeeper.getInstance());
    this.context = context;
    this.requestIdGenerator = requestIdGenerator;
  }

  protected final NotificationWebSocketRequestIdGenerator getRequestIdGenerator() {
    return requestIdGenerator;
  }

  @Override
  protected final ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected final WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + PATH);
  }

  @Override
  protected final void checkErrorMessage(AnyWebSocketMessage message)
      throws WebSocketRuntimeException {}
}
