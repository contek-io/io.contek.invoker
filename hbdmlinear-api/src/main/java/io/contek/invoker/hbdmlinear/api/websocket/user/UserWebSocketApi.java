package io.contek.invoker.hbdmlinear.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdmlinear.api.websocket.common.notification.NotificationWebSocketApi;

import java.util.HashMap;
import java.util.Map;

public final class UserWebSocketApi extends NotificationWebSocketApi {

  private final Map<TriggerOrderChannel.Id, TriggerOrderChannel> tradeDetailChannels =
      new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public TriggerOrderChannel getTriggerOrderChannel(TriggerOrderChannel.Id id) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          id,
          k -> {
            TriggerOrderChannel result = new TriggerOrderChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
