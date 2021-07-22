package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.Map;

@NotThreadSafe
public final class WebSocketRequest extends AnyWebSocketMessage {
  private static int nextId = 0;
  public String method;
  public Integer id;
  public String jsonrpc = "2.0";
  public Map<String, List<String>> params;

  public WebSocketRequest() {
    this.id = nextId;
    nextId += 1;
  }
}
