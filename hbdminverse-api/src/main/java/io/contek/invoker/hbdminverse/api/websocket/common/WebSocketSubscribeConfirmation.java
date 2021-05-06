package io.contek.invoker.hbdminverse.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSubscribeConfirmation extends WebSocketConfirmation {

  public String subbed;
}
