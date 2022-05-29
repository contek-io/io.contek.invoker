package io.contek.invoker.deribit.api.websocket.common;

public abstract class WebSocketChannelMessage<P extends Params> extends WebSocketInboundMessage {

  public String jsonrpc;
  public String method;
  public P params;
}
