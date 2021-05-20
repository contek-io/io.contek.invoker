package io.contek.invoker.hbdmlinear.api.websocket.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;

@ThreadSafe
public final class UserWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;
  private final UserWebSocketRequestIdGenerator requestIdGenerator;

  private final Map<TriggerOrderChannel.Id, TriggerOrderChannel> tradeDetailChannels =
      new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    this(actor, context, new UserWebSocketRequestIdGenerator());
  }

  private UserWebSocketApi(
      IActor actor, WebSocketContext context, UserWebSocketRequestIdGenerator requestIdGenerator) {
    super(
        actor,
        WebSocketMessageParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        IWebSocketLiveKeeper.noOp());
    this.context = context;
    this.requestIdGenerator = requestIdGenerator;
  }

  public TriggerOrderChannel getTriggerOrderChannel(TriggerOrderChannel.Id id) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          id,
          k -> {
            TriggerOrderChannel result = new TriggerOrderChannel(k, requestIdGenerator);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_IP_WEB_SOCKET_CONNECTION_REQUEST;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/linear-swap-notification");
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
