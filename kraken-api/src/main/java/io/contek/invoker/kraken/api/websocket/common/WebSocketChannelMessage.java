package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketChannelMessage extends WebSocketResponse {

  public Integer channelID;
  public String channelName;
  public String pair;
}
