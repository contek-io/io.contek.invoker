package io.contek.invoker.binancefutures.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketCommand extends AnyWebSocketMessage {

  public String method;
  public List<String> params;
  public Integer id;
}
