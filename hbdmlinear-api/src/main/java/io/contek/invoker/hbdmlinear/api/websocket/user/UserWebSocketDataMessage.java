package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class UserWebSocketDataMessage<T> extends UserWebSocketInboundMessage {

  public T data;
}
