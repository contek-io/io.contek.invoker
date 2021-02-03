package io.contek.invoker.deribit.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NotThreadSafe
public final class WebSocketSubscriptionRequest extends WebSocketOutboundMessage {
  public Integer id;
  public String jsonrpc = "2.0";
  public Map<String, List<String>> params;
  private static int nextId = 0;

  public WebSocketSubscriptionRequest() {
    this.params = new HashMap<>();
    this.id = nextId;
    nextId += 1;
    this.params.put("channels", Collections.emptyList());
  }
}
