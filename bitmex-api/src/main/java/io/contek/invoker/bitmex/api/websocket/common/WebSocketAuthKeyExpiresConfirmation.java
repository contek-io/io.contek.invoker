package io.contek.invoker.bitmex.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketAuthKeyExpiresConfirmation extends WebSocketRequestConfirmation {

  public String authKeyExpires;
}
