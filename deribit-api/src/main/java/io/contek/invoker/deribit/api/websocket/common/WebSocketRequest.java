package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public final class WebSocketRequest<Params> extends AnyWebSocketMessage {

  public String jsonrpc = "2.0";
  public Integer id;
  public String method;
  public Params params;
}
