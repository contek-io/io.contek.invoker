package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final Map<UserOrdersChannel.Id, UserOrdersChannel> userOrdersChannels = new HashMap<>();
  private final Map<UserChangesChannel.Id, UserChangesChannel> userChangesChannels =
      new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public UserOrdersChannel getUserOrdersChannel(UserOrdersChannel.Id id) {
    synchronized (userOrdersChannels) {
      return userOrdersChannels.computeIfAbsent(
          id,
          k -> {
            UserOrdersChannel result = new UserOrdersChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }

  public UserChangesChannel getUserChangesChannel(UserChangesChannel.Id id) {
    synchronized (userChangesChannels) {
      return userChangesChannels.computeIfAbsent(
          id,
          k -> {
            UserChangesChannel result = new UserChangesChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
