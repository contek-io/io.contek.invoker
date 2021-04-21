package io.contek.invoker.hbdmlinear.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketUnsubscribeRequest extends WebSocketRequest {

  public String unsub;
}
