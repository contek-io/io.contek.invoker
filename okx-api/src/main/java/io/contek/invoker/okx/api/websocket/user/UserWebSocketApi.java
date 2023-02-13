package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.okx.api.websocket.WebSocketApi;
import io.contek.invoker.security.ICredential;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;

  private final Map<OrdersChannel.Id, OrdersChannel> ordersChannels = new HashMap<>();
  private final Map<PositionsChannel.Id, PositionsChannel> positionsChannels = new HashMap<>();

  public UserWebSocketApi(String name, IActor actor, WebSocketContext context) {
    super(name, actor);
    this.context = context;
  }

  public OrdersChannel getOrdersChannel(String type, @Nullable String instId) {
    synchronized (ordersChannels) {
      return ordersChannels.computeIfAbsent(
          OrdersChannel.Id.of(type, instId),
          k -> {
            OrdersChannel result = new OrdersChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public PositionsChannel getPositionsChannel(String type, @Nullable String instId) {
    synchronized (positionsChannels) {
      return positionsChannels.computeIfAbsent(
          PositionsChannel.Id.of(type, instId),
          k -> {
            PositionsChannel result = new PositionsChannel(k);
            attach(result);
            return result;
          });
    }
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl() + "/ws/v5/private");
  }
}
