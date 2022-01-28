package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketAuthenticationMessage extends WebSocketOutboundMessage {

  public Args args;

  @NotThreadSafe
  public static final class Args {

    public String key;
    public String sign;
    public Long time;
    public String subaccount;
  }
}
