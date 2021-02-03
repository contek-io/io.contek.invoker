package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NotThreadSafe
public final class WebSocketRequest extends AnyWebSocketMessage {
  public String method;
  public Integer id;
  public String jsonrpc = "2.0";
  public Map<String, List<String>> params;
  private static int nextId = 0;

  public WebSocketRequest() {
    this.params = new HashMap<>();
    this.id = nextId;
    nextId += 1;
    this.params.put("channels", Collections.emptyList());
  }
}
