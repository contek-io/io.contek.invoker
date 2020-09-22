package io.contek.invoker.bitmex.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscribeConfirmation extends WebSocketRequestConfirmation {

  public String subscribe;
}
