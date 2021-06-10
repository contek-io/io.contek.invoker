package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class NotificationWebSocketSubscriptionRequest extends NotificationWebSocketRequest {

  public String topic;
}
