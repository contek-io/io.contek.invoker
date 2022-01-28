package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketChannelArg {

  public String channel;
  public String instType;
  public String uly;
  public String instId;
}
