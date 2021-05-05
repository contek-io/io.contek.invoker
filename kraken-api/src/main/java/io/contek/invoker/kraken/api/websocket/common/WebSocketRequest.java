package io.contek.invoker.kraken.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketRequest extends AnyWebSocketMessage {

  public String event;
  public Integer reqid;
  public List<String> pair;
  public Subscription subscription;
}
