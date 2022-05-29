package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketApi;

import java.util.HashMap;
import java.util.Map;

public final class MarketNotificationWebSocketApi extends NotificationWebSocketApi {

  private final Map<LiquidationOrderChannel.Id, LiquidationOrderChannel> liquidationOrderChannels =
      new HashMap<>();

  public MarketNotificationWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public LiquidationOrderChannel getLiquidationOrderChannel(LiquidationOrderChannel.Id id) {
    synchronized (liquidationOrderChannels) {
      return liquidationOrderChannels.computeIfAbsent(
          id,
          k -> {
            LiquidationOrderChannel result =
                new LiquidationOrderChannel(id, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
