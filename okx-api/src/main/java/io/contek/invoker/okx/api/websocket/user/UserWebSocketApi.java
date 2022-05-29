package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketCall;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.okx.api.websocket.WebSocketApi;
import io.contek.invoker.security.ICredential;

import java.util.HashMap;
import java.util.Map;

public final class UserWebSocketApi extends WebSocketApi {

  private final WebSocketContext context;

  private final Map<OrdersChannel.Id, OrdersChannel> ordersChannels = new HashMap<>();
  private final Map<PositionsChannel.Id, PositionsChannel> positionsChannels = new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor);
    this.context = context;
  }

  public OrdersChannel getOrdersChannel(OrdersChannel.Id id) {
    synchronized (ordersChannels) {
      return ordersChannels.computeIfAbsent(
          id,
          k -> {
            OrdersChannel result = new OrdersChannel(k);
            attach(result);
            return result;
          });
    }
  }

  public PositionsChannel getPositionsChannel(PositionsChannel.Id id) {
    synchronized (positionsChannels) {
      return positionsChannels.computeIfAbsent(
          id,
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
