package io.contek.invoker.binanceinverse.api.websocket.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binanceinverse.api.rest.user.UserRestApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.binanceinverse.api.ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;

@ThreadSafe
public final class UserWebSocketApi extends BaseWebSocketApi {

  private final AtomicReference<AccountUpdateChannel> accountUpdateChannel =
      new AtomicReference<>();
  private final AtomicReference<OrderUpdateChannel> orderUpdateChannel = new AtomicReference<>();
  private final AtomicReference<MarginCallChannel> marginCallChannel = new AtomicReference<>();
  private final AtomicReference<AccountConfigUpdateChannel> accountConfigUpdateChannel =
      new AtomicReference<>();

  private final WebSocketContext context;

  public UserWebSocketApi(IActor actor, WebSocketContext context, UserRestApi userRestApi) {
    super(
        actor,
        UserWebSocketParser.getInstance(),
        IWebSocketAuthenticator.noOp(),
        new UserWebSocketLiveKeeper(userRestApi, actor.getClock()));
    this.context = context;
  }

  public AccountUpdateChannel getAccountUpdateChannel() {
    synchronized (accountUpdateChannel) {
      AccountUpdateChannel channel = accountUpdateChannel.get();
      if (channel == null) {
        channel = new AccountUpdateChannel();
        attach(channel);
        accountUpdateChannel.set(channel);
      }
      return channel;
    }
  }

  public OrderUpdateChannel getOrderUpdateChannel() {
    synchronized (orderUpdateChannel) {
      OrderUpdateChannel channel = orderUpdateChannel.get();
      if (channel == null) {
        channel = new OrderUpdateChannel();
        attach(channel);
        orderUpdateChannel.set(channel);
      }
      return channel;
    }
  }

  public MarginCallChannel getMarginCallChannel() {
    synchronized (marginCallChannel) {
      MarginCallChannel channel = marginCallChannel.get();
      if (channel == null) {
        channel = new MarginCallChannel();
        attach(channel);
        marginCallChannel.set(channel);
      }
      return channel;
    }
  }

  public AccountConfigUpdateChannel getLeverageUpdateChannel() {
    synchronized (accountConfigUpdateChannel) {
      AccountConfigUpdateChannel channel = accountConfigUpdateChannel.get();
      if (channel == null) {
        channel = new AccountConfigUpdateChannel();
        attach(channel);
        accountConfigUpdateChannel.set(channel);
      }
      return channel;
    }
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
  protected void checkErrorMessage(AnyWebSocketMessage message) throws WebSocketRuntimeException {
    if (message instanceof UserDataStreamExpiredEvent) {
      throw new WebSocketSessionExpiredException();
    }
  }
}
