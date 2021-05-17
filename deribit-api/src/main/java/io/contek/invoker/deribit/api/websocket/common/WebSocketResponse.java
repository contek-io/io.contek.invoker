package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.deribit.api.common._Error;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketResponse extends WebSocketInboundMessage {

  public Integer id;
  public String jsonrpc;
  public List<String> result;
  public _Error error;
}
