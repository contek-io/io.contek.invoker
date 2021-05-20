package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class UserWebSocketSubscriptionRequest extends UserWebSocketRequest {

  public String topic;
}
