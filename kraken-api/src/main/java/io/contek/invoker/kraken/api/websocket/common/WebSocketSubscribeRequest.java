package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketSubscribeRequest extends WebSocketRequest {

  public List<String> pair;
  public Subscription subscription;
}
