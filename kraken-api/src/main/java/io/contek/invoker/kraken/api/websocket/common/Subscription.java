package io.contek.invoker.kraken.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Subscription {

  public String name;
  public Integer depth;
  public Integer interval;
  public String token;
}
