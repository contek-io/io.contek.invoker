package io.contek.invoker.okx.api.websocket.common;

import java.util.List;

public abstract class WebSocketRequest<Arg> extends WebSocketOutboundMessage {

  public String op;
  public List<Arg> args;
}
