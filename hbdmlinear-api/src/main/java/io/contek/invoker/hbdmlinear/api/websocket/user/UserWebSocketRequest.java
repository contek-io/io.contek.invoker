package io.contek.invoker.hbdmlinear.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class UserWebSocketRequest extends AnyWebSocketMessage {

  public String op;
  public String topic;
  public String cid;
}
