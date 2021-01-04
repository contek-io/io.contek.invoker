package io.contek.invoker.bitmex.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketInfo extends AnyWebSocketMessage {

  public String info;
  public String version;
  public String timestamp;
  public String docs;
  public WebSocketRateLimit limit;
}
