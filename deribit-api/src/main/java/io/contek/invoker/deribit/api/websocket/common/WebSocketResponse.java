package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.deribit.api.common._Error;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketResponse<R> extends WebSocketInboundMessage {

  public Integer id;
  public String jsonrpc;
  public R result;
  public _Error error;
}
