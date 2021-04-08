package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.rest.user.PostListenKey;
import io.contek.invoker.binancefutures.api.rest.user.UserRestApi;
import io.contek.invoker.binancefutures.api.websocket.WebSocketApi;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;
  private final UserRestApi userRestApi;
  public AccountUpdateChannel accountUpdateChannel;
  public OrderUpdateChannel orderUpdateChannel;
  public MarginCallChannel marginCallChannel;
  public LeverageUpdateChannel leverageUpdateChannel;
  public String listenKey;

  public UserWebSocketApi(IActor actor, WebSocketContext context, UserRestApi userRestApi) {
    super(actor);
    this.context = context;
    this.userRestApi = userRestApi;
  }

  public AccountUpdateChannel getAccountUpdateChannel() {
    if (accountUpdateChannel == null) {
      accountUpdateChannel = new AccountUpdateChannel();
      attach(accountUpdateChannel);
    }
    return accountUpdateChannel;
  }

  public OrderUpdateChannel getOrderUpdateChannel() {
    if (orderUpdateChannel == null) {
      orderUpdateChannel = new OrderUpdateChannel();
      attach(orderUpdateChannel);
    }
    return orderUpdateChannel;
  }

  public MarginCallChannel getMarginCallChannel() {
    if (marginCallChannel == null) {
      marginCallChannel = new MarginCallChannel();
      attach(marginCallChannel);
    }
    return marginCallChannel;
  }

  public LeverageUpdateChannel getLeverageUpdateChannel() {
    if (leverageUpdateChannel == null) {
      leverageUpdateChannel = new LeverageUpdateChannel();
      attach(leverageUpdateChannel);
    }
    return leverageUpdateChannel;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    PostListenKey.Response newListenKey = userRestApi.postListenKey().submit();
    listenKey = newListenKey.listenKey;
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/" + listenKey);
  }

  @Override
  protected void preHeartBeat() {
    userRestApi.putListenKey().setListenKey(listenKey).submit();
  }
}
