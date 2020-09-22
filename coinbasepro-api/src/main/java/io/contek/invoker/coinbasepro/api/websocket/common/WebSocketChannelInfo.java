package io.contek.invoker.coinbasepro.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketChannelInfo {

  public String name;
  public List<String> product_ids;
}
