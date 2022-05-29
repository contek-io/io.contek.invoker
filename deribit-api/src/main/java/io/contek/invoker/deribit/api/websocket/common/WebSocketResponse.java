package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.deribit.api.common._Error;

public abstract class WebSocketResponse<R> extends WebSocketInboundMessage {

  public Integer id;
  public String jsonrpc;
  public R result;
  public _Error error;
}
