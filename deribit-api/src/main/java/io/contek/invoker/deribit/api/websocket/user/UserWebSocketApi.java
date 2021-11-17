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
  private final Map<UserPositionsChannel.Id, UserPositionsChannel> userPositionsChannels =
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

  public UserPositionsChannel getUserPositionsChannel(UserPositionsChannel.Id id) {
    synchronized (userPositionsChannels) {
      return userPositionsChannels.computeIfAbsent(
          id,
          k -> {
            UserPositionsChannel result = new UserPositionsChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
