package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class UserWebSocketChannelMessage extends UserWebSocketInboundMessage {

  public String topic;
}
