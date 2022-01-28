package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class WebSocketRequest<Arg> extends WebSocketOutboundMessage {

  public String op;
  public List<Arg> args;
}
