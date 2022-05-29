package io.contek.invoker.ftx.api.websocket.common;

public final class WebSocketAuthenticationMessage extends WebSocketOutboundMessage {

  public Args args;

  public static final class Args {

    public String key;
    public String sign;
    public Long time;
    public String subaccount;
  }
}
