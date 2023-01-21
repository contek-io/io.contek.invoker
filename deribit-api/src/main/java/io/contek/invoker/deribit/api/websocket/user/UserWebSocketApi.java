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
  private final Map<UserTradesChannel.Id, UserTradesChannel> userTradesChannels = new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public UserOrdersChannel getUserOrdersChannel(String kind, String currency, String interval) {
    return getUserOrdersChannel(UserOrdersChannel.Id.of(kind, currency, interval));
  }

  public UserOrdersChannel getUserOrdersChannel(String instrumentName, String interval) {
    return getUserOrdersChannel(UserOrdersChannel.Id.of(instrumentName, interval));
  }

  public UserChangesChannel getUserChangesChannel(String kind, String currency, String interval) {
    return getUserChangesChannel(UserChangesChannel.Id.of(kind, currency, interval));
  }

  public UserChangesChannel getUserChangesChannel(String instrumentName, String interval) {
    return getUserChangesChannel(UserChangesChannel.Id.of(instrumentName, interval));
  }

  public UserTradesChannel getUserTradesChannel(String kind, String currency, String interval) {
    return getUserTradesChannel(UserTradesChannel.Id.of(kind, currency, interval));
  }

  public UserTradesChannel getUserTradesChannel(String instrumentName, String interval) {
    return getUserTradesChannel(UserTradesChannel.Id.of(instrumentName, interval));
  }

  private UserOrdersChannel getUserOrdersChannel(UserOrdersChannel.Id id) {
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

  private UserChangesChannel getUserChangesChannel(UserChangesChannel.Id id) {
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

  private UserTradesChannel getUserTradesChannel(UserTradesChannel.Id id) {
    synchronized (userTradesChannels) {
      return userTradesChannels.computeIfAbsent(
          id,
          k -> {
            UserTradesChannel result = new UserTradesChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
