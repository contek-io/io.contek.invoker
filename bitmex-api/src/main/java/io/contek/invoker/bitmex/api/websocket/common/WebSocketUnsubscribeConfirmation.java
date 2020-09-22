package io.contek.invoker.bitmex.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketUnsubscribeConfirmation extends WebSocketRequestConfirmation {

  public String unsubscribe;
}
