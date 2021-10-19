package io.contek.invoker.binancespot.api.websocket.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.rest.user.UserRestApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;

@ThreadSafe
public final class UserWebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  public UserWebSocketApi(IActor actor, WebSocketContext context, UserRestApi userRestApi) {
    super(
        actor,
        UserWebSocketParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        new UserWebSocketLiveKeeper(userRestApi, actor.getClock()));
    this.context = context;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    UserWebSocketLiveKeeper liveKeeper = (UserWebSocketLiveKeeper) getLiveKeeper();
    String listenKey = liveKeeper.init();
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/" + listenKey);
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_WEB_SOCKET_CONNECTION;
  }

  @Override
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {}
}
