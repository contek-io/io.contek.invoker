package io.contek.invoker.hbdminverse.api.websocket.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdminverse.api.websocket.common.notification.NotificationWebSocketApi;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserWebSocketApi extends NotificationWebSocketApi {

  private final Map<TriggerOrderChannel.Id, TriggerOrderChannel> tradeDetailChannels =
      new HashMap<>();

  public UserWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }

  public TriggerOrderChannel getTriggerOrderChannel(String contractCode) {
    synchronized (tradeDetailChannels) {
      return tradeDetailChannels.computeIfAbsent(
          TriggerOrderChannel.Id.of(contractCode),
          k -> {
            TriggerOrderChannel result = new TriggerOrderChannel(k, getRequestIdGenerator());
            attach(result);
            return result;
          });
    }
  }
}
