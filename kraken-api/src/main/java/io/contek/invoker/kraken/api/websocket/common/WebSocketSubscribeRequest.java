package io.contek.invoker.kraken.api.websocket.common;

import java.util.List;

public final class WebSocketSubscribeRequest extends WebSocketRequest {

  public List<String> pair;
  public Subscription subscription;
}
