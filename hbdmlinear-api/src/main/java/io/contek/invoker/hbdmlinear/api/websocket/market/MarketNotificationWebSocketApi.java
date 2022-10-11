package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class MarketNotificationWebSocketApi extends NotificationWebSocketApi {

  private final Map<LiquidationOrderChannel.Id, LiquidationOrderChannel> liquidationOrderChannels =
      new HashMap<>();

  public MarketNotificationWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public LiquidationOrderChannel getLiquidationOrderChannel(String contractCode) {
    synchronized (liquidationOrderChannels) {
      return liquidationOrderChannels.computeIfAbsent(
          LiquidationOrderChannel.Id.of(contractCode),
          k -> {
            LiquidationOrderChannel result =
                new LiquidationOrderChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
